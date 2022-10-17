package com.clean.application.services;

import com.clean.application.identity.services.UserDetailsImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class UserService {
    private SecurityContext CurrentContext;
    public UserService(){
        CurrentContext = SecurityContextHolder.getContext();
    }

    public Long getUserId(){
        UserDetailsImpl userDetails = (UserDetailsImpl) CurrentContext.getAuthentication()
                .getPrincipal();
        return userDetails.getId();
    }

    public UserDetailsImpl GetCurrentUser(){
        return (UserDetailsImpl) CurrentContext.getAuthentication()
                .getPrincipal();
    }

}
