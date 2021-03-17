package com.codegym.blog.controller;


import com.codegym.blog.model.Category;
import com.codegym.blog.service.inteface.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //Find All
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ResponseEntity<List<Category>> responseEntity = new ResponseEntity<>(categories, HttpStatus.OK);
        return responseEntity;
    }

    //Find One
    @GetMapping("/{id}")
    public ResponseEntity<Category> categoryDetail(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            return new ResponseEntity<>(category.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Create
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            categoryService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Update
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        categoryService.findById(id);
        return categoryService.save(category);
    }


}
