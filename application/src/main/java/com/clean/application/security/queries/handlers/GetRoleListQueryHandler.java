package com.clean.application.security.queries.handlers;

import com.clean.application.security.models.RoleModel;
import com.clean.application.security.queries.GetRoleListQuery;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.auth.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetRoleListQueryHandler implements IRequestHandler<GetRoleListQuery, List<RoleModel>> {
    private RoleRepository roleRepository;

    public GetRoleListQueryHandler(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleModel> handle(GetRoleListQuery request) {
        return roleRepository.findAll().stream().map(RoleModel::Map).collect(Collectors.toList());
    }
}
