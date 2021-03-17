package com.codegym.blog.controller;

import com.codegym.blog.model.Response;
import com.codegym.blog.model.User;
import com.codegym.blog.service.inteface.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/register")
@AllArgsConstructor
public class RegistrationController {
    @Autowired
    private final RegistrationService registrationService;

    @PostMapping()
    public Response register(@RequestBody User user) {
        registrationService.register(user);
        return new Response();
    }
    
    @GetMapping()
    public Response verify(@RequestParam(name = "token") String token){
        registrationService.verify(token);
        return new Response();
    }
}
