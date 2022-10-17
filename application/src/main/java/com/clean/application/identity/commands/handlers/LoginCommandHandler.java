package com.clean.application.identity.commands.handlers;

import com.clean.application.identity.commands.LoginCommand;
import com.clean.application.identity.models.JwtResponse;
import com.clean.application.identity.services.UserDetailsImpl;
import com.clean.common.jwt.JwtUtils;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.sec.Users;
import com.clean.persistence.auth.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class LoginCommandHandler implements IRequestHandler<LoginCommand, JwtResponse> {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtUtils jwtUtils;

    @Autowired
    public LoginCommandHandler(AuthenticationManager authenticationManager, JwtUtils jwtUtils,
            UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
    }

    @Override
    public JwtResponse handle(LoginCommand request) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (org.springframework.security.authentication.CredentialsExpiredException ex) {
            return JwtResponse.builder().passwordExpired(true).build();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(user.getUsername());

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        
        Users users = userRepository.findById(userDetails.getId()).orElseThrow(() -> new RuntimeException("User Not Found!"));
        users.setLastLoginDate(Timestamp.from(Instant.now()));
        userRepository.save(users);

        return JwtResponse.builder().token(jwt).id(userDetails.getId()).username(userDetails.getUsername())
                .fullName(userDetails.getFullName()).email(userDetails.getEmail()).build();
    }
}
