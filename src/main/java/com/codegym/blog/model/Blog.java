package com.codegym.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private String shortDescription;
    private String previewImageURL;
    private LocalDateTime createdAt;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(name = "blog_tags_info",
            joinColumns = @JoinColumn(name = "blogId"),
            inverseJoinColumns = @JoinColumn(name = "tagId")
    )
    private Set<Tag> tags;


    private Long views = 0l;

    // TODO : logic verify ?
//    private boolean verified;

    private boolean status = false;

}
