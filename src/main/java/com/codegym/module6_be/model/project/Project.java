package com.codegym.module6_be.model.project;

import com.codegym.module6_be.model.user.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String title;

    private Long type;

    @ManyToOne
    private User projectOwner;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> users;
}
