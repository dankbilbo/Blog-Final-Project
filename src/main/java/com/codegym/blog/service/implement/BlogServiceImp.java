package com.codegym.blog.service.implement;

import com.codegym.blog.model.Blog;
import com.codegym.blog.model.Tag;
import com.codegym.blog.model.User;
import com.codegym.blog.repository.BlogRepository;
import com.codegym.blog.repository.TagRepository;
import com.codegym.blog.service.inteface.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BlogServiceImp implements BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    TagRepository tagRepository;

    @Override
    public List<Blog> findAll(int page, int size) {
        return null;
    }

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Optional<Blog> findById(String id) {
        return blogRepository.findById(id);
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(String id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Blog addNewBlog(Blog blog) {
        User user = new User();
        user.setId("USER202103170001");
        blog.setUser(user);
        // TODO : SET USER ID
        String blogId = generateId();
        blog.setId(blogId);

        blog.setCreatedAt(LocalDateTime.now());

        List<Tag> blogTagsToDB = new ArrayList<>();
        List<Tag> blogTags = blog.getTags();
        for (Tag tag : blogTags) {
            boolean tagAlreadyExisted = tagRepository.findByName(tag.getName()).isPresent();
            if (!tagAlreadyExisted) {
                tagRepository.save(tag);
            }
            blogTagsToDB.add(tagRepository.findByName(tag.getName()).get());
        }
        blog.setTags(blogTagsToDB);

        return save(blog);
    }

    @Override
    public Blog updateBlog(Blog blog) {
        Blog blogToUpdate = blogRepository.findById(blog.getId()).get();

        blogToUpdate.setTitle(blog.getTitle());
        blogToUpdate.setContent(blog.getContent());
        blogToUpdate.setPreviewImageURL(blog.getPreviewImageURL());
        blogToUpdate.setShortDescription(blog.getShortDescription());

        return save(blogToUpdate);
    }

    String generateId() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
        String formatedDate = dateFormat.format(date);
        String blogCode = "BLOG" + formatedDate;
        Optional<Blog> lastBlogAddedToday = blogRepository.findLastBlogAddedToday(blogCode + "%");
        boolean blogAddedTodayExist = lastBlogAddedToday.isPresent();

        if (blogAddedTodayExist) {
            String lastAddedNumber = lastBlogAddedToday.get().getId().substring(12, 16);
            Double lastAddedId = Double.parseDouble(lastAddedNumber);
            Double blogIdNumber = lastAddedId + 1.0d;
            String addedBlogNumber = BigDecimal.valueOf(blogIdNumber / 10000).toPlainString();
            String blogIdaddedToDatabase = addedBlogNumber.substring(2, 6);
            blogCode = blogCode + blogIdaddedToDatabase;
        } else {
            blogCode = blogCode + "0001";
        }
        return blogCode;
    }
}
