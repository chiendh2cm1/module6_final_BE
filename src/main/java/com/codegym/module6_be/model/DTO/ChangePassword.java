package com.codegym.module6_be.model.DTO;

import lombok.Data;

@Data
public class ChangePassword {
    private String oldPassword;

    private String newPassword;
}
