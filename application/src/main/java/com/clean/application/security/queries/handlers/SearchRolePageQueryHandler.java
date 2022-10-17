package com.clean.application.security.queries.handlers;

import com.clean.application.security.models.RolePagesModel;
import com.clean.application.security.queries.SearchRolePageQuery;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.sec.RolePage;
import com.clean.persistence.sec.RolePageRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SearchRolePageQueryHandler implements IRequestHandler<SearchRolePageQuery, List<RolePagesModel>> {
    private RolePageRepository rolePageRepository;

    public SearchRolePageQueryHandler(RolePageRepository rolePageRepository){
        this.rolePageRepository = rolePageRepository;
    }

    @Override
    public List<RolePagesModel> handle(SearchRolePageQuery request) {
        return rolePageRepository.findAllByRoleId(request.getRoleId()).stream().map(RolePagesModel::Map)
                .collect(Collectors.toList());
    }
}
