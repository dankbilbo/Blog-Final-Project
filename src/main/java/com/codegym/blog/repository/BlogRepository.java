package com.codegym.blog.repository;

import com.codegym.blog.model.Blog;
import com.codegym.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface BlogRepository extends JpaRepository<Blog,String> {

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM blog " +
                    "WHERE id like ?1" +
                    " ORDER BY created_at DESC" +
                    " LIMIT 1")
    Optional<Blog> findLastBlogAddedToday(String blogCode);
}
