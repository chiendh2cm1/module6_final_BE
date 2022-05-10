package com.codegym.module6_be.model.comment;
import com.codegym.module6_be.model.task.Task;
import com.codegym.module6_be.model.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String date;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Task task;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private User user;
}
