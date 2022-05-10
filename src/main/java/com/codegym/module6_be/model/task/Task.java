package com.codegym.module6_be.model.task;

import com.codegym.module6_be.model.label.Label;
import com.codegym.module6_be.model.status.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private int position;

    @ManyToOne
    private Status status;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Label> labels;
}
