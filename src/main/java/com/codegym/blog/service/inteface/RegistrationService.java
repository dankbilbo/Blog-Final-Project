package com.codegym.blog.service.inteface;

import com.codegym.blog.model.User;

public interface RegistrationService {
    User register(User user);
    void verify(String token);
}
