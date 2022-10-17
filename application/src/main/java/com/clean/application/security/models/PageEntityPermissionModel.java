package com.clean.application.security.models;

import com.clean.domain.entity.sec.EntityPermission;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageEntityPermissionModel {
    private Integer id;
    private String code;
    private String title;

    public static PageEntityPermissionModel Map(EntityPermission entity){
        return PageEntityPermissionModel.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .title(entity.getTitle())
                .build();
    }
}
