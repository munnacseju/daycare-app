package com.daycare.app.backend.controllers;

import java.util.HashMap;

import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daycare.app.backend.services.EmailService;
import com.daycare.app.backend.services.UserService;
// import com.daycare.app.backend.util.EmailService;
import com.daycare.app.backend.models.User;


@RestController
public class HomeController {
	
	@Autowired
	private UserService userService;

	
    @GetMapping("/")
    public HashMap<String, String> home() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        HashMap<String, String> values = new HashMap<>();
        values.put("hello", "Welcome!");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authUserEmail = (authentication.getName());
        User user = userService.findByEmail(authUserEmail).get();
        values.put("user name", user.getName());
        values.put("User Email", authUserEmail);
        return values;
    }
}
