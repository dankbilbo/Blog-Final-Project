package com.codegym.blog.controller;

import com.codegym.blog.model.Response;
import com.codegym.blog.model.User;
import com.codegym.blog.service.inteface.RegistrationService;
import com.codegym.blog.service.inteface.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/register")
@AllArgsConstructor
public class RegistrationController {
    @Autowired
    private final RegistrationService registrationService;

    @Autowired
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<User> register(@Validated @RequestBody User user) {
        boolean emailExisted = userService.findByEmail(user.getEmail()).isPresent();
        boolean userNameExisted = userService.findByUsername(user.getUsername()).isPresent();
        if (emailExisted || userNameExisted){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(registrationService.register(user),HttpStatus.CREATED);
    }
    
    @GetMapping("/token/{token}")
    public ResponseEntity verify(@PathVariable(name = "token") String token){
        registrationService.verify(token);
        return new ResponseEntity(HttpStatus.OK);
    }
}
