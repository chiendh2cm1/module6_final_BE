package com.codegym.module6_be.model.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
