package com.clean.application.security.models;

import com.clean.domain.entity.sec.RolePageEntityPermission;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolePageEntityPermissionModel {
    private Integer rolePageId;
    private Integer entityPermissionId;

    public static RolePageEntityPermissionModel Map(RolePageEntityPermission entity) {
        return RolePageEntityPermissionModel.builder()
                .rolePageId(entity.getRolePageId())
                .entityPermissionId(entity.getEntityPermissionId())
                .build();
    }
}
