package com.clean.application.identity.commands.handlers;

import java.sql.Timestamp;
import java.time.Instant;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.common.validation.StringValidation;
import com.clean.domain.entity.sec.PasswordRequest;
import com.clean.persistence.auth.PasswordRequestRepository;

public class RequestPasswordCommandHandler implements IRequestHandler<RequestPasswordCommand,Boolean > {
    private PasswordRequestRepository passwordRequestRepository;

    public RequestPasswordCommandHandler(PasswordRequestRepository passwordRequestRepository){
        this.passwordRequestRepository = passwordRequestRepository;
    }

    @Override
    public Boolean handle(RequestPasswordCommand request) {
        
        if(!StringValidation.IsNullOrEmpty(request.getEmail())){

            if(!passwordRequestRepository.existsByEmailAndProcessed(request.getEmail(),false)){

                PasswordRequest req = PasswordRequest.builder()
                    .email(request.getEmail())
                    .createdOn(Timestamp.from(Instant.now()))
                    .processed(false)
                    .build();

                this.passwordRequestRepository.save(req);
            }
            return true;
        }
        else{
            throw new RuntimeException("Email Is Invalid!");
        }
    }
    
}
