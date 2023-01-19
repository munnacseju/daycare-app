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
import com.daycare.app.backend.models.Order;
import com.daycare.app.backend.models.User;
import com.daycare.app.backend.services.OrderService;
import com.daycare.app.backend.services.UserService;

@RestController
@RequestMapping("/api")
public class OrderController {
    public static final String ADD_ORDER = "/addOrder";
    public static final String FIND_ORDER_BY_ID = "/findOrder/{id}";
    public static final String FIND_ALL_ORDER_BY_USER = "/findAllOrder";
    public static final String DELETE_ORDER = "/deleteOrder/{id}";

    @Autowired
    private UserService userService;
    
    @Autowired
    private OrderService orderService;
    
    @RequestMapping(value = ADD_ORDER, method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> addOrder(@RequestBody Order order) {
        HashMap<String, Object> response = new HashMap<>();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userService.findByEmail(email);
        User user = userOptional.get();
        order.setUser(user);
        orderService.save(order);
        response.put("order", order);
        return response;
    }

    @RequestMapping(value = FIND_ALL_ORDER_BY_USER, method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> findOrdersByUser() {
        HashMap<String, Object> response = new HashMap<>();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userService.findByEmail(email);
        User user = userOptional.get();
        response.put("status", CaregiverConstant.STATUS.OK);
        response.put("orders", orderService.findByUser(user));
        // orderService.findByUser(user);
        return response;
    }

    @RequestMapping(value = DELETE_ORDER, method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> deleteOrder(@PathVariable Long id) {
        HashMap<String, Object> response = new HashMap<>();
        orderService.deleteById(id);
        response.put("order deleted", id);
        return response;
    }
}
