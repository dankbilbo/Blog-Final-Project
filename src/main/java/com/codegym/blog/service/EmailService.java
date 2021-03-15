package com.codegym.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public interface EmailService {
    void sendEmail(String to, String body, String topic);
}
