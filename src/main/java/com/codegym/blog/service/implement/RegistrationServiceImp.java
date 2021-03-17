package com.codegym.blog.service.implement;

import com.codegym.blog.model.User;
import com.codegym.blog.service.inteface.EmailService;
import com.codegym.blog.service.inteface.RegistrationService;
import com.codegym.blog.service.inteface.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceImp implements RegistrationService {

    @Autowired
    private final UserService userService;

    @Autowired
    private final EmailService emailService;

    @Override
    public String register(User user) {
        String token = userService.signUpUser(user);

        String linkVerify = "http://localhost:8080/register?token=" + token;
        String content = "please verify your account by clicking this link " + linkVerify;
        String topic = "Pro Hub verify account";
        emailService.sendEmail(user.getEmail(),content,topic);
        return "email sended to " + user.getEmail();
    }

    @Override
    public void verify(String token) {

    }


}
