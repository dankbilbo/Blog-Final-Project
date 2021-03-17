package com.codegym.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String content;
    private String timeComment;

    @ManyToOne
    private User user;

    @OneToOne
    private Comment repliedTo;

    @ManyToOne
    private Blog blog;
}
