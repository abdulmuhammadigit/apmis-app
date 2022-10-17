package com.clean.application.identity.commands.handlers;

import com.clean.application.identity.commands.ResetUserPasswordCommand;
import com.clean.common.AppConfig;
import com.clean.common.exceptions.EntityNotFoundException;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.sec.Users;
import com.clean.persistence.auth.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

public class ResetUserPasswordCommandHandler implements IRequestHandler<ResetUserPasswordCommand,Boolean>{
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public ResetUserPasswordCommandHandler(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Boolean handle(ResetUserPasswordCommand request) {
        Users user = userRepository.findById(request.getId())
            .orElseThrow(() -> new EntityNotFoundException("User",request.getId()));
        
        user.setPassword(passwordEncoder.encode(AppConfig.DEFAULT_PASSWORD));

        userRepository.save(user);
        return true;
    }

}
