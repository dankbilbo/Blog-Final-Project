package com.codegym.blog.service.implement;

import com.codegym.blog.model.UserVerificationToken;
import com.codegym.blog.repository.UserVerificationTokenRepository;
import com.codegym.blog.service.inteface.UserVerificationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserVerificationTokenServiceImp implements UserVerificationTokenService {

    @Autowired
    private final UserVerificationTokenRepository verificationTokenRepository;

    @Override
    public List<UserVerificationToken> findAll(int page, int size) {
        return null;
    }

    @Override
    public List<UserVerificationToken> findAll() {
        return null;
    }

    @Override
    public UserVerificationToken save(UserVerificationToken userVerificationToken) {
        return verificationTokenRepository.save(userVerificationToken);
    }

    @Override
    public Optional<UserVerificationToken> findById(String token) {
        return Optional.empty();
    }



    @Override
    public Optional<UserVerificationToken> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Optional<UserVerificationToken> getToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public void verifyToken(String token) {
        verificationTokenRepository.verifyToken(LocalDateTime.now(),token);
    }
}
