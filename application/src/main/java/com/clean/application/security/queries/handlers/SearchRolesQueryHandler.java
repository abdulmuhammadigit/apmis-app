package com.clean.application.security.queries.handlers;

import java.util.List;
import java.util.stream.Collectors;

import com.clean.application.security.models.RoleModel;
import com.clean.application.security.queries.SearchRolesQuery;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.auth.RoleRepository;

public class SearchRolesQueryHandler implements IRequestHandler<SearchRolesQuery,List<RoleModel>> {
    private RoleRepository roleRepository;

    public SearchRolesQueryHandler(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleModel> handle(SearchRolesQuery request) {
        return roleRepository.findByNameContains(request.getName())
            .stream().map(RoleModel::Map).collect(Collectors.toList());
    }
    
}
