package com.clean.application.security.queries.handlers;

import com.clean.application.security.models.RoleModel;
import com.clean.application.security.queries.SaveRoleCommand;
import com.clean.common.exceptions.EntityNotFoundException;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.sec.Roles;
import com.clean.persistence.auth.RoleRepository;

public class SaveRoleCommandHandler implements IRequestHandler<SaveRoleCommand,RoleModel> {
    private RoleRepository roleRepository;

    public SaveRoleCommandHandler(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleModel handle(SaveRoleCommand request) {
        Roles role ;
        if(request.getId() != null){
            role = roleRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Role",request.getId()));
        }
        else{
            role = new Roles();
        }

        role.setName( request.getName());

        roleRepository.save(role);

        return RoleModel.Map(role);
    }
    
}
