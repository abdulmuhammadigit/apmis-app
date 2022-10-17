package com.clean.application.identity.commands.handlers;

import java.sql.Timestamp;
import java.time.Instant;

import com.clean.application.identity.commands.ChangePasswordCommand;
import com.clean.application.identity.models.JwtResponse;
import com.clean.application.identity.models.PasswordChangeModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.sec.PasswordHistory;
import com.clean.domain.entity.sec.Users;
import com.clean.persistence.auth.PasswordHistoryRepository;
import com.clean.persistence.auth.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

public class ChangePasswordCommandHandler implements IRequestHandler<ChangePasswordCommand,PasswordChangeModel> {
    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private PasswordHistoryRepository historyRepository;

    public ChangePasswordCommandHandler(UserRepository userRepository,PasswordEncoder encoder,PasswordHistoryRepository historyRepository){
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.historyRepository = historyRepository;
    }

    @Override
    public PasswordChangeModel handle(ChangePasswordCommand request) {
        PasswordChangeModel result = new  PasswordChangeModel();
        result.setSuccess(false);

        if(request.getPassword().equals(request.getNewPassword())){
            throw new RuntimeException("Passwords Should Be Different!");
        }
        else{

            Users user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid User Name Or Password!"));
                
            if(encoder.matches(request.getPassword(), user.getPassword())){
                historyRepository.save(
                    PasswordHistory.builder()
                    .userId(user.getId())
                    .passwordHash(user.getPassword())
                    .createdOn(Timestamp.from(Instant.now()))
                    .build()
                );

                user.setPassword(encoder.encode(request.getNewPassword()));
                user.setPasswordChangedDate(Timestamp.from(Instant.now()));
                user.setPasswordExpired(false);
                
                userRepository.save(user);
                result.setSuccess(true);    
            }
            else{
                throw new RuntimeException("Invalid User Name Or Password!");
            }
        }

        return result;
    }
    
}
