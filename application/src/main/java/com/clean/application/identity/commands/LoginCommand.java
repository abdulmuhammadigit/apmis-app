package com.clean.application.identity.commands;

import com.clean.application.identity.models.JwtResponse;
import com.clean.common.mediator.core.IRequest;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginCommand implements IRequest<JwtResponse> {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}

