package com.codegym.blog.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserVerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDate createdAt;
    private LocalDate verifiedAt;
    private LocalDate confirmedAt;

    @ManyToOne
    private User user;

    public UserVerificationToken(String token, LocalDate createdAt, LocalDate verifiedAt, LocalDate confirmedAt, User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.verifiedAt = verifiedAt;
        this.confirmedAt = confirmedAt;
        this.user = user;
    }
}
