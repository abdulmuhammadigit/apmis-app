package com.clean.application.security.queries.handlers;

import com.clean.application.security.models.RolePageEntityPermissionModel;
import com.clean.application.security.queries.SearchRolePagePermissionsQuery;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.sec.RolePageEntityPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class SearchRolePagePermissionsQueryHandler implements IRequestHandler<SearchRolePagePermissionsQuery, List<RolePageEntityPermissionModel>> {
    private RolePageEntityPermissionRepository rolePageEntityPermissionRepository;

    @Autowired
    public SearchRolePagePermissionsQueryHandler(
            RolePageEntityPermissionRepository rolePageEntityPermissionRepository
    ){
        this.rolePageEntityPermissionRepository = rolePageEntityPermissionRepository;
    }

    @Override
    public List<RolePageEntityPermissionModel> handle(SearchRolePagePermissionsQuery request) {
        return rolePageEntityPermissionRepository.findAllByRolePageIdAndEntityId(request.getRolePageId(),request.getEntityId())
        .stream().map(RolePageEntityPermissionModel::Map).collect(Collectors.toList());
    }
}
