package com.codegym.blog.service.inteface;

import java.util.List;
import java.util.Optional;

public interface Service<T> {
    List<T> findAll(int page, int size);

    List<T> findAll();

    T save(T t);

    Optional<T> findById(String id);

    Optional<T> findById(Long id);

    void delete(Long id);

    void delete(String id);
}
