package com.clean.application.security.models;

import com.clean.domain.entity.sec.RolePage;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolePagesModel {
    private Integer id;
    private Integer roleId;
    private Integer pageId;

    private String role;
    private String page;

    public static RolePagesModel Map(RolePage entity) {
        return RolePagesModel.builder()
                .id(entity.getId())
                .roleId(entity.getRoleId())
                .pageId(entity.getPageId())
                .build();
    }
}
