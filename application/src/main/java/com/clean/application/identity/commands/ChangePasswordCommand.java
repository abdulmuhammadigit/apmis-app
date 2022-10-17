package com.clean.application.identity.commands;

import com.clean.application.identity.models.PasswordChangeModel;
import com.clean.common.mediator.core.IRequest;

import lombok.Data;

@Data
public class ChangePasswordCommand implements IRequest<PasswordChangeModel> {
    private String username;
    private String password;
    private String newPassword;
}
