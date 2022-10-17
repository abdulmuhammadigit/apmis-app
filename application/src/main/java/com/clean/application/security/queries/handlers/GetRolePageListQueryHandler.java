package com.clean.application.security.queries.handlers;

import com.clean.application.security.models.RolePagesModel;
import com.clean.application.security.queries.GetRolePageListQuery;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.sec.RolePageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class GetRolePageListQueryHandler implements IRequestHandler<GetRolePageListQuery, List<RolePagesModel>> {
    private RolePageRepository rolePageRepository;

    @Autowired
    public GetRolePageListQueryHandler(RolePageRepository rolePageRepository){
        this.rolePageRepository = rolePageRepository;
    }

    @Override
    public List<RolePagesModel> handle(GetRolePageListQuery request) {
        return this.rolePageRepository.findAllByRoleIdWithRoleAndPage(request.getRoleId())
                .stream().map(c -> {
                    RolePagesModel model = RolePagesModel.Map(c);
                    model.setRole(c.getRole().getName());
                    model.setPage(c.getPage().getTitle());
                    return model;
                }).collect(Collectors.toList());
    }
}
