package com.clean.application.users.queries.handlers;

import java.util.stream.Collectors;

import com.clean.application.users.models.UserModel;
import com.clean.application.users.queries.FindUserQuery;
import com.clean.common.exceptions.EntityNotFoundException;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;



public class FindUserQueryHandler implements IRequestHandler<FindUserQuery, UserModel> {
    private UserRepository userRepository;

    @Autowired
    public FindUserQueryHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel handle(FindUserQuery request) {
        return userRepository.findById(request.getId())
        .map(cur-> {
            UserModel model = UserModel.Map(cur);
            model.setRoleIds(cur.getRoles().stream().map(cr -> cr.getId()).collect(Collectors.toList()));
            model.setStageIds(cur.getStages().stream().map(cr -> cr.getId()).collect(Collectors.toList()));
            return model;
        })
        .orElseThrow(() -> new EntityNotFoundException("User",request.getId()));
    }
}
