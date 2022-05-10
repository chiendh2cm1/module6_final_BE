package com.codegym.module6_be.model.label;

import com.codegym.module6_be.model.board.Board;
import com.codegym.module6_be.model.color.Color;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private Board board;

    @OneToOne
    private Color color;
}
