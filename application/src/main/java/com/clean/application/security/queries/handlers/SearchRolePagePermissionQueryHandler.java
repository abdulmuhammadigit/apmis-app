package com.clean.application.security.queries.handlers;

import com.clean.application.security.models.EntityPermissionListModel;
import com.clean.application.security.models.PageEntityPermissionModel;
import com.clean.application.security.queries.SearchRolePageEntityPermissionsQuery;
import com.clean.common.exceptions.EntityNotFoundException;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.sec.RolePage;
import com.clean.persistence.sec.EntityPermissionRepository;
import com.clean.persistence.sec.PageEntityRepository;
import com.clean.persistence.sec.RolePageEntityPermissionRepository;
import com.clean.persistence.sec.RolePageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchRolePagePermissionQueryHandler implements IRequestHandler<SearchRolePageEntityPermissionsQuery, List<EntityPermissionListModel>> {
    private RolePageRepository rolePageRepository;
    private PageEntityRepository pageEntityRepository;
    private EntityPermissionRepository entityPermissionRepository;

    @Autowired
    public SearchRolePagePermissionQueryHandler(
            RolePageRepository rolePageRepository,
            PageEntityRepository pageEntityRepository,
            EntityPermissionRepository entityPermissionRepository
    ){
        this.rolePageRepository = rolePageRepository;
        this.pageEntityRepository = pageEntityRepository;
        this.entityPermissionRepository = entityPermissionRepository;
    }

    @Override
    public List<EntityPermissionListModel> handle(SearchRolePageEntityPermissionsQuery request) {
        RolePage rolePage = rolePageRepository.findById(request.getRolePageId()).orElseThrow(() -> new EntityNotFoundException("Role Page"));

        List<PageEntityPermissionModel> permissions = entityPermissionRepository.findAll().stream().map(PageEntityPermissionModel::Map).collect(Collectors.toList());


        return pageEntityRepository.findAllByPageIdWithEntityAndPermissions(rolePage.getPageId()).stream().map(cur -> {
            EntityPermissionListModel model = new EntityPermissionListModel();
            model.setId(cur.getEntityId());
            model.setTitle(cur.getEntity().getTitle());
            model.setPermissions(permissions);
            return model;
        }).collect(Collectors.toList());
    }
}
