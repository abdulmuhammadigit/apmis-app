package com.clean.application.security.queries.handlers;

import com.clean.application.security.models.RoleModel;
import com.clean.application.security.queries.FindRoleQuery;
import com.clean.common.exceptions.EntityNotFoundException;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.auth.RoleRepository;

public class FindRoleQueryHandler implements IRequestHandler<FindRoleQuery,RoleModel> {
    private RoleRepository roleRepository;

    public FindRoleQueryHandler(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleModel handle(FindRoleQuery request) {
        return roleRepository.findById(request.getId())
            .map(RoleModel::Map).orElseThrow(() -> new EntityNotFoundException("Role",request.getId()));
    }
    
}
