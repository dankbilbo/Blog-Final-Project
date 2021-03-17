package com.codegym.blog.service.implement;

import com.codegym.blog.model.User;
import com.codegym.blog.model.UserVerificationToken;
import com.codegym.blog.repository.UserVerificationTokenRepository;
import com.codegym.blog.service.inteface.EmailService;
import com.codegym.blog.service.inteface.RegistrationService;
import com.codegym.blog.service.inteface.UserService;
import com.codegym.blog.service.inteface.UserVerificationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationServiceImp implements RegistrationService {

    @Autowired
    private final UserService userService;

    @Autowired
    private final EmailService emailService;

    @Autowired
    private final UserVerificationTokenService verificationTokenService;

    @Override
    public User register(User user) {
        String token = userService.signUpUser(user);
        String linkVerify = "http://localhost:8080/register/token/" + token;
        String content = "please verify your account by clicking this link " + linkVerify;
        String topic = "Pro Hub verify account";
        emailService.sendEmail(user.getEmail(),content,topic);
        return user;
    }

    @Override
    public void verify(String token) {
        UserVerificationToken verificationToken
                = verificationTokenService.getToken(token)
                .orElseThrow(() ->new IllegalStateException("token not found"));
        ;
        if (verificationToken.getVerifiedAt() != null){
            throw new IllegalStateException("email confirmed already");
        }

        LocalDateTime expiredAt = verificationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token expired");
        }

        verificationTokenService.verifyToken(token);

        userService.enableUserByEmail(verificationToken.getUser().getEmail());
    }


}
