package com.codegym.blog.service.inteface;

import com.codegym.blog.model.Blog;

public interface BlogService extends Service<Blog> {
    Blog addNewBlog(Blog blog);
    Blog updateBlog(Blog blog);
}
