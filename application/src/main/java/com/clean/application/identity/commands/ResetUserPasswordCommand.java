package com.clean.application.identity.commands;

import com.clean.common.mediator.core.IRequest;
import lombok.Data;

@Data
public class ResetUserPasswordCommand implements IRequest<Boolean> {
    private long id;
}
