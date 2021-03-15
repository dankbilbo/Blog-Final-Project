package com.codegym.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    @Id
    private String id;
    private String title;
    private String content;
    private LocalDate createdAt;

    @ManyToOne
    private Category category;


    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(name = "blog_Tags_info",
            joinColumns = @JoinColumn(name = "blogId"),
            inverseJoinColumns = @JoinColumn(name = "tagId")
    )
    private Set<Tag> tags;


    private Long views = 0l;

    // TODO : logic verify ?
//    private boolean verified;

    private boolean status = false;

    public Blog(String id, String title, String content, Category category, User user, Set<Tag> tags, boolean status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.user = user;
        this.tags = tags;
        this.status = status;
    }
}
