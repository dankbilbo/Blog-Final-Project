package com.codegym.blog.controller;

import com.codegym.blog.model.Blog;
import com.codegym.blog.service.inteface.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    BlogService blogService;

    //FindAll
    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlog() {
        List<Blog> blogs = blogService.findAll();
        if (blogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ResponseEntity<List<Blog>> responseEntity = new ResponseEntity<>(blogs, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping()
    public ResponseEntity<Blog> addBlog(@RequestBody Blog blog) {
        blogService.addNewBlog(blog);
        return new ResponseEntity<>(blogService.addNewBlog(blog), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable("id") String id, @RequestBody Blog blog) {
        boolean blogExisted = blogService.findById(id).isPresent();
        if (blogExisted) {
            blog.setId(id);
            return new ResponseEntity<>(blogService.updateBlog(blog), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBLog(@PathVariable("id") String id){
        boolean blogExisted = blogService.findById(id).isPresent();
        if (blogExisted){
            blogService.delete(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable("id") String id){
        Optional<Blog> findBlog = blogService.findById(id);
        boolean blogExisted = findBlog.isPresent();
        if (blogExisted){
            return new ResponseEntity<>(findBlog.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
