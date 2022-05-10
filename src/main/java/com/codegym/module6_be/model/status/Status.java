package com.codegym.module6_be.model.status;

import com.codegym.module6_be.model.board.Board;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;   

    private int position;

}
