package com.daycare.app.backend.controllers;

import static com.daycare.app.backend.security.SecurityConstants.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.daycare.app.backend.models.User;
import com.daycare.app.backend.services.EmailService;
import com.daycare.app.backend.services.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api")
public class AuthController {
    public static final String REGISTER = "/register";
    public static final String GOOGLE_OAUTH_LOG_IN = "/google-oauth-login";
    public static final String LOGGED_USER_DETAILS = "/me";
    public static final String GOOGLE_OAUTH_TOKEN_INFO_URL = "https://oauth2.googleapis.com/tokeninfo?id_token=";
    public static final String VERIFY_PIN = "/verifypin/{pin}";

    @Autowired
    private UserService userService;

    	
	@Autowired
	private EmailService emailService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @RequestMapping(value = REGISTER, method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> registerUser(@RequestBody User user) {
        HashMap<String, Object> response = new HashMap<>();
        Optional<User> existsUser = userService.findByEmail(user.getEmail());
        if (existsUser.isPresent()) {
            response.put("status", "EMAIL_EXISTS");
            response.put("error", "User is already registered!");
            return response;
        }
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        
        Random r = new Random();
        int low = 10000;
        int high = 99999;
        int result = r.nextInt(high-low) + low;
        String verificationPin = String.valueOf(result);
//        Random random = new Random(System.currentTimeMillis());
        user.setVerificationPin(verificationPin);
        user.setIsVerified(false);
        
        userService.save(user);
        response.put("status", "OK");
        response.put("message", "Successfully registered!");
        emailService.sendSimpleMessage(user.getEmail(), "Daycare account verification code", "Hello, Your Daycare account varification code is "+verificationPin);
        System.out.println("Mail send "+user.getEmail());
        return response;
    }

    @RequestMapping(value = GOOGLE_OAUTH_LOG_IN, method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> openLogIn(@RequestParam(value = "idToken", defaultValue = "") String idTokenString) {
        HashMap<String, Object> response = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(GOOGLE_OAUTH_TOKEN_INFO_URL + idTokenString,
                String.class);
        JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        String email = jsonObject.getString("email");
        Optional<User> optionalUser = userService.findByEmail(email);
        if (!optionalUser.isPresent()) {
            String name = jsonObject.getString("name");
            User user = new User(name, email, "");
            userService.save(user);
        }
        Claims claims = Jwts.claims().setSubject(email);
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        claims.put("roles", roles);
        String token = Jwts.builder().setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        response.put("access_token", TOKEN_PREFIX + token);
        response.put("status", "OK");
        return response;
    }

    @RequestMapping(value = LOGGED_USER_DETAILS, method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> loggedUserDetails() {
        HashMap<String, Object> response = new HashMap<>();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userService.findByEmail(email);
        User user = userOptional.get();
        response.put("status", "OK");
        response.put("data", user);
        return response;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { HttpClientErrorException.class })
    HashMap<String, Object> handleMethodArgumentNotValid(HttpClientErrorException ex) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("status", "NOT_OK");
        response.put("description", "this is not a valid token from google!");
        return response;
    }
    
    @RequestMapping(value = VERIFY_PIN, method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> verificationPin(@PathVariable String pin) {
        HashMap<String, Object> response = new HashMap<>();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userService.findByEmail(email);
        User user = userOptional.get();
        if(pin.equals(user.getVerificationPin())) {
        	user.setIsVerified(true);
            userService.save(user);
            response.put("status", "verified");
            System.out.println("user virification updated");
        }else {
        	response.put("status", "Not verified");
            System.out.println("code not equal" + "pin " + pin);
            return response;
        }

        response.put("user", user);
        return response;
    }
}
