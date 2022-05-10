package com.codegym.module6_be.model.file;

import com.codegym.module6_be.model.task.Task;
import com.codegym.module6_be.model.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TaskFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Task task;

    @ManyToOne
    private User user;
}
