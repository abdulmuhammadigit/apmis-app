package com.clean.application.security.commands.handlers;

import com.clean.application.security.commands.SaveRolePageEntityPermissionCommand;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.sec.RolePageEntityPermission;
import com.clean.persistence.sec.RolePageEntityPermissionRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SaveRolePageEntityPermissionCommandHandler implements IRequestHandler<SaveRolePageEntityPermissionCommand,Boolean> {
    private RolePageEntityPermissionRepository rolePageEntityPermissionRepository;

    public SaveRolePageEntityPermissionCommandHandler(
            RolePageEntityPermissionRepository rolePageEntityPermissionRepository
    ){
        this.rolePageEntityPermissionRepository = rolePageEntityPermissionRepository;
    }

    @Override
    public Boolean handle(SaveRolePageEntityPermissionCommand request) {
        List<RolePageEntityPermission> permissionList = rolePageEntityPermissionRepository.findAllByRolePageIdAndEntityId(request.getRolePageId(),request.getEntityId());

        List<RolePageEntityPermission> toDelete = permissionList.stream().filter(cur -> !request.getPermissionIds().contains(cur.getEntityPermissionId())).collect(Collectors.toList());

        List<Integer> entityPermissionIds = permissionList.stream().map(RolePageEntityPermission::getEntityPermissionId).collect(Collectors.toList());

        List<Integer> toCreate = request.getPermissionIds().stream().filter(cur -> !entityPermissionIds.contains(cur)).collect(Collectors.toList());

        rolePageEntityPermissionRepository.deleteAll(toDelete);

        toCreate.forEach(cur -> {
            rolePageEntityPermissionRepository.save(
                    RolePageEntityPermission.builder()
                        .rolePageId(request.getRolePageId())
                        .entityId(request.getEntityId())
                        .entityPermissionId(cur)
                    .build()

            );
        });

        return true;
    }
}
