package com.codegym.module6_be.model.user;

import lombok.Data;

@Data
public class ChangePassword {
    private String oldPassword;

    private String newPassword;
}
