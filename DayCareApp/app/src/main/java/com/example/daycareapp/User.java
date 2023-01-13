package com.example.daycareapp;


public class User {
    private String username;
    private String password;
    private String email;
    private String name;

    public User(String username, String password, String email, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


}
