package com.codegym.blog.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Like {
    @Id
    private Long id;
    private boolean status = false;

}
