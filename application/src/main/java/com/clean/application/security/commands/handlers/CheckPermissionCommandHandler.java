package com.clean.application.security.commands.handlers;

import com.clean.application.security.commands.CheckPermissionCommand;
import com.clean.application.utils.EntityPermissions;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.sec.Entity;
import com.clean.domain.entity.sec.RolePageEntityPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class CheckPermissionCommandHandler implements IRequestHandler<CheckPermissionCommand,Boolean> {
    private EntityManager entityManager;

    @Autowired
    public CheckPermissionCommandHandler(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Boolean handle(CheckPermissionCommand request) {
        List<String> roles = request.getAuthentication()
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        TypedQuery<RolePageEntityPermission> query = entityManager.createQuery(
                "select rpe from RolePage rp " +
                " inner join Roles r on rp.roleId = r.id " +
                " inner join Pages p on rp.pageId = p.id " +
                " inner join PageEntity pe on p.id = pe.pageId " +
                " inner join RolePageEntityPermission rpe on rpe.rolePageId = rp.id " +
                " where r.name in :roles and rpe.entity.code = :entity and rpe.entityPermission.code = :permission", RolePageEntityPermission.class);

        query.setParameter("entity",request.getEntityCode());
        if(request.getPermissionCode().equals(EntityPermissions.SAVE)){
            if(request.getId() != null){
                query.setParameter("permission",EntityPermissions.UPDATE);
            }
            else{
                query.setParameter("permission",EntityPermissions.INSERT);
            }
        }
        else{
            query.setParameter("permission",request.getPermissionCode());
        }
        query.setParameter("roles",roles);

        List<RolePageEntityPermission> permissions = query.getResultList();

        return permissions.isEmpty();
    }
}
