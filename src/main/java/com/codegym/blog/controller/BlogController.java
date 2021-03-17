package com.codegym.blog.controller;

import com.codegym.blog.model.Blog;
import com.codegym.blog.service.inteface.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    BlogService blogService;

    //FindAll
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Blog>> getAllBlog() {
        List<Blog> blogs = blogService.findAll();
        if (blogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ResponseEntity<List<Blog>> responseEntity = new ResponseEntity<>(blogs, HttpStatus.OK);
        return responseEntity;
    }




}
