package com.codegym.module6_be.model.board;

import com.codegym.module6_be.model.project.Project;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private Project project;
}
