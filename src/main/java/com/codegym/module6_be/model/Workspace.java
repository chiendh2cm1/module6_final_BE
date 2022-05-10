package com.codegym.module6_be.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String title;
    @NotEmpty
    private String type;
    @NotEmpty
    private String privacy;

    @ManyToOne
    private User owner;

    @OneToMany
    private Set<MemberWorkspace> members;

    @OneToMany
    private Set<Board> boards;

}
