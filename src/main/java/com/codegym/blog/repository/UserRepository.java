package com.codegym.blog.repository;

import com.codegym.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String name);
    Optional<User> findByEmail(String email);


    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM user " +
                    "WHERE id like ?1" +
                    " ORDER BY created_at DESC" +
                    " LIMIT 1")
    Optional<User> findLastUserAddedToday(String userCode);


    @Query(nativeQuery = true,
            value = "UPDATE user " +
                    "SET enabled = TRUE " +
                    "WHERE email = ?1")
    @Modifying
    void enableUser(String email);

}
