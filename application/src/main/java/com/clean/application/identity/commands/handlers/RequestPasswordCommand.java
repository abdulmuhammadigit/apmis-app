package com.clean.application.identity.commands.handlers;

import com.clean.common.mediator.core.IRequest;

import lombok.Data;

@Data
public class RequestPasswordCommand implements IRequest<Boolean> {
    private String email;
}
