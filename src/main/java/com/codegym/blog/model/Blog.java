package com.codegym.blog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty
    private String id;
    @JsonProperty
    private String title;
    @JsonProperty
    private String content;
    @JsonProperty
    private String shortDescription;
    @JsonProperty
    private String previewImageURL;
    @JsonProperty
    private LocalDateTime createdAt;

    @ManyToOne
    @JsonProperty
    private Category category;

    @ManyToOne
    @JsonProperty
    private User user;

    @ManyToMany
    @JoinTable(name = "blog_tags_info",
            joinColumns = @JoinColumn(name = "blogId"),
            inverseJoinColumns = @JoinColumn(name = "tagId")
    )
    @JsonProperty
    private Set<Tag> tags;

    @JsonProperty
    private Long views = 0l;

    // TODO : logic verify ?
//    private boolean verified;

    private boolean status = false;

}
