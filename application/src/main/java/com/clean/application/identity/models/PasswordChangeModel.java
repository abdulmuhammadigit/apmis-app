package com.clean.application.identity.models;

import lombok.Data;

@Data
public class PasswordChangeModel {
    private boolean success;
    private String message;
}
