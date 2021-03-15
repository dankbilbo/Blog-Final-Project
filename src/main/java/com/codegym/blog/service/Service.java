package com.codegym.blog.service;

import java.util.List;
import java.util.Optional;

public interface Service <T>{
    List<T> findALl();
    T save(T t);
    Optional<T> findById(String id);
    Optional<T> findById(Long id);
    void delete(Long id);
    void delte(String id);
}
