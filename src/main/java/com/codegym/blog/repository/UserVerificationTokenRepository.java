package com.codegym.blog.repository;

import com.codegym.blog.model.UserVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVerificationTokenRepository extends JpaRepository<UserVerificationToken,Long> {
}
