package com.codegym.blog.service.inteface;


import com.codegym.blog.model.User;

public interface UserService extends Service<User> {

    String signUpUser(User user);

}
