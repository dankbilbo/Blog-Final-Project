package com.codegym.blog.service.inteface;


import com.codegym.blog.model.User;

import java.util.Optional;

public interface UserService extends Service<User> {

    String signUpUser(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    void enableUserByEmail(String email);

}
