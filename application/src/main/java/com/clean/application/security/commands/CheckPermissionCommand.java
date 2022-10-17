package com.clean.application.security.commands;

import com.clean.common.mediator.core.IRequest;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

@Data
@Builder
public class CheckPermissionCommand implements IRequest<Boolean> {
    private Authentication authentication;
    private Serializable id;
    private String entityCode;
    private String permissionCode;
}
