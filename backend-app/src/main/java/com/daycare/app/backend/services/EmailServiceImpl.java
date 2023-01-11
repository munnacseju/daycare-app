package com.daycare.app.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    // @Autowired
    // public SimpleMailMessage template;

    @Value("spring.mail.username")
    private String MAIL_USERNAME;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(MAIL_USERNAME);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

//    public void sendMessageUsingTemplate(String to, String subject) {
//        String sender = "7 Digital Ton Ltd";
//        String text = String.format(template.getText(), sender);
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(MAIL_USERNAME);
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        emailSender.send(message);
//    }
//
//    @Override
//    public void sendMessageWithAttachment(
//        String to, String subject, String text, String pathToAttachment) {
//
//        try {
//            MimeMessage message = emailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//            helper.setFrom(MAIL_USERNAME);
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(text);
//
//            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
//            helper.addAttachment("Invoice", file);
//
//            emailSender.send(message);
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//    }
}