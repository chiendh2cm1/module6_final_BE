package com.codegym.module6_be.model;

public interface DetailedMember {
    Long getId();
    boolean getCanEdit();
    Long getBoardId();
    Long getUserId();
    String getUsername();
    String getNickname();
    String getImage();
}