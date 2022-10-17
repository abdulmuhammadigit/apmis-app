package com.clean.presentation.config;

import com.clean.application.security.commands.CheckPermissionCommand;
import com.clean.common.mediator.core.IMediator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class AccessPermissionEvaluator implements PermissionEvaluator {
    private static final Logger logger = LoggerFactory.getLogger(AccessPermissionEvaluator.class);

    private final IMediator mediator;

    public AccessPermissionEvaluator(IMediator mediator){
        this.mediator = mediator;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        logger.error("Calling Permission Evaluator!");
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String entityCode, Object permission) {
        try {
            return mediator.send(
                    CheckPermissionCommand.builder()
                        .authentication(authentication)
                        .id(serializable)
                        .entityCode(entityCode)
                        .permissionCode(permission.toString())
                        .build()
            );
        }
        catch (Exception ex){
            logger.error("Exception Handling Permission ",ex);
        }
        return false;
    }
}
