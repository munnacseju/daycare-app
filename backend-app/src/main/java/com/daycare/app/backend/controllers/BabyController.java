package com.daycare.app.backend.controllers;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.daycare.app.backend.constant.CaregiverConstant;
import com.daycare.app.backend.models.Baby;
import com.daycare.app.backend.models.User;
import com.daycare.app.backend.services.BabyService;
import com.daycare.app.backend.services.UserService;

@RestController
@RequestMapping("/api")
public class BabyController {
    public static final String ADD_BABY = "/addBaby";
    public static final String FIND_BABY_BY_ID = "/findBaby/{id}";
    public static final String FIND_ALL_BABY_BY_USER = "/findAllBaby";
    public static final String DELETE_BABY = "/deleteBaby/{id}";

    @Autowired
    private UserService userService;
    
    @Autowired
    private BabyService babyService;
    
    @RequestMapping(value = ADD_BABY, method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> addBaby(@RequestBody Baby baby) {
        HashMap<String, Object> response = new HashMap<>();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userService.findByEmail(email);
        User user = userOptional.get();
        baby.setUser(user);
        baby.setId(null);
        babyService.save(baby);
        response.put("baby", baby);
        response.put("status", CaregiverConstant.STATUS.OK);

        return response;
    }

    @RequestMapping(value = FIND_ALL_BABY_BY_USER, method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> findBabiesByUser() {
        HashMap<String, Object> response = new HashMap<>();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userService.findByEmail(email);
        User user = userOptional.get();
        response.put("babies", babyService.findByUser(user));
        response.put("status", CaregiverConstant.STATUS.OK);
        // babyService.findByUser(user);
        return response;
    }

    @RequestMapping(value = DELETE_BABY, method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> deleteBaby(@PathVariable Long id) {
        HashMap<String, Object> response = new HashMap<>();
        babyService.deleteById(id);
        response.put("status", CaregiverConstant.STATUS.OK);
        response.put("message", "Successfully baby deleted");
        return response;
    }
}
