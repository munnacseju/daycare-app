package com.daycare.app.backend.services;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);

//    public void sendMessageUsingTemplate(String to, String subject);
//
//    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);
}
