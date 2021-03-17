package com.codegym.blog.repository;

import com.codegym.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String name);
    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true,
            value = "SELECT user.id " +
                    "FROM user " +
                    "WHERE user.id like ?1" +
                    "ORDER BY user.created_at DESC" +
                    "LIMIT 1")
    Optional<User> findLastUserAdded();

}
