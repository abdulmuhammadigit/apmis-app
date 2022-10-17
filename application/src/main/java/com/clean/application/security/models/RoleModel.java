package com.clean.application.security.models;

import com.clean.domain.entity.sec.Roles;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleModel {
    private Integer id;
    private String name;

    public static RoleModel Map(Roles role){
        return RoleModel.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
