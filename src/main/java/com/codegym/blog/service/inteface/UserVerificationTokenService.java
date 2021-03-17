package com.codegym.blog.service.inteface;

import com.codegym.blog.model.UserVerificationToken;

import java.util.Optional;

public interface UserVerificationTokenService extends Service<UserVerificationToken>{
    Optional<UserVerificationToken> getToken(String token);
    void verifyToken(String token);
}
