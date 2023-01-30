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
import com.daycare.app.backend.models.Caregiver;
import com.daycare.app.backend.models.User;
import com.daycare.app.backend.models.UserRole;
import com.daycare.app.backend.services.CaregiverService;
import com.daycare.app.backend.services.UserService;

@RestController
@RequestMapping("/api")
public class CaregiverController {
    public static final String ADD_CAREGIVER = "/addCaregiver";
    public static final String FIND_ALL_CAREGIVER = "/findAllCaregiver";
    public static final String DELETE_CAREGIVER = "/deleteCaregiver/{id}";
    public static final String FIND_CAREGIVER = "/findCaregiver/{id}";


    @Autowired
    private UserService userService;
    
    @Autowired
    private CaregiverService caregiverService;
    
    @RequestMapping(value = ADD_CAREGIVER, method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> addCaregiver(@RequestBody Caregiver caregiver) {
        HashMap<String, Object> response = new HashMap<>();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userService.findByEmail(email);
        User user = userOptional.get();
        if(!user.getUserRole().equalsIgnoreCase(UserRole.ADMIN.toString())){
            System.out.println(user.getUserRole() + " " + UserRole.ADMIN.toString());
            response.put("status", CaregiverConstant.STATUS.NOT_OK);
            response.put("message", "You can't change caregiver");
            response.put("caregiver", caregiver);
            return response;
        }         
        caregiver.setUser(user);
        caregiverService.save(caregiver);
        response.put("status", CaregiverConstant.STATUS.OK);
        response.put("message", "Added Successfully");
        response.put("caregiver", caregiver);
        return response;
    }

    @RequestMapping(value = FIND_ALL_CAREGIVER, method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> findBabiesByUser() {
        HashMap<String, Object> response = new HashMap<>();
        // String email = SecurityContextHolder.getContext().getAuthentication().getName();
        // Optional<User> userOptional = userService.findByEmail(email);
        // User user = userOptional.get();
        response.put("status", CaregiverConstant.STATUS.OK);
        response.put("caregivers", caregiverService.findAll());
        // caregiverService.findByUser(user);
        return response;
    }

    @RequestMapping(value = DELETE_CAREGIVER, method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> deleteCaregiver(@PathVariable Long id) {
        HashMap<String, Object> response = new HashMap<>();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userService.findByEmail(email);
        User user = userOptional.get();
        if(!user.getUserRole().equalsIgnoreCase(UserRole.ADMIN.toString())){
            response.put("status", CaregiverConstant.STATUS.NOT_OK);
            response.put("message", "You can't change caregiver");
            return response;
        }    
        caregiverService.deleteById(id);
        response.put("status", CaregiverConstant.STATUS.OK);
        response.put("message", "caregiver deleted!");
        return response;
    }

    @RequestMapping(value = FIND_CAREGIVER, method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> findCaregiver(@PathVariable Long id) {
        HashMap<String, Object> response = new HashMap<>();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userService.findByEmail(email);
        User user = userOptional.get();
        // if(!user.getUserRole().equalsIgnoreCase(UserRole.ADMIN.toString())){
        //     response.put("status", CaregiverConstant.STATUS.NOT_OK);
        //     response.put("message", "You can't change caregiver");
        //     return response;
        // }    
        Optional<Caregiver> caregiverOptional = caregiverService.findById(id);
        if(caregiverOptional.isEmpty()){
            response.put("status", CaregiverConstant.STATUS.NOT_OK);
            response.put("message", "Invalid caregiver id");
            return response;
        }
        response.put("status", CaregiverConstant.STATUS.OK);
        response.put("message", "caregiver found");
        response.put("caregiver", caregiverOptional.get());
        return response;
    }
}
