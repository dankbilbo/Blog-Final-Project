package com.codegym.blog.service.implement;

import com.codegym.blog.model.User;
import com.codegym.blog.model.UserVerificationToken;
import com.codegym.blog.repository.UserRepository;
import com.codegym.blog.repository.UserVerificationTokenRepository;
import com.codegym.blog.security.PasswordEncoder;
import com.codegym.blog.service.inteface.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService, UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder encoder;

    @Autowired
    private final UserVerificationTokenRepository verificationTokenRepository;

    @Override
    public List<User> findAll(int page, int size) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }


    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username + "not found"));
    }

    //register
    @Override
    public String signUpUser(User user){
        boolean emailExisted = userRepository.findByEmail(user.getEmail()).isPresent();
        if (emailExisted){
            throw new IllegalStateException(user.getEmail() + " existed");
        }
        boolean usernameExisted = userRepository.findByUsername(user.getUsername()).isPresent();
        if (usernameExisted){
            throw new IllegalStateException(user.getUsername() + "existed");
        }

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
        String formatedDate = dateFormat.format(date);

        String userID = "USER" + formatedDate;
        user.setId(userID);

        String userSignUpPassword = user.getPassword();
        String passwordEncripted = encoder.encoder().encode(userSignUpPassword);
        user.setPassword(passwordEncripted);

        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        verificationTokenRepository.save(
                new UserVerificationToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    user
                ));


        return token;
    }
}
