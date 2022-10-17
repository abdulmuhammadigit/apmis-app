package com.clean.application.security.models;

import com.clean.domain.entity.sec.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityModel {
    private Integer id;
    private String code;
    private String title;
    private String path;

    public static EntityModel Map(Entity rec){
        return EntityModel.builder()
        .id(rec.getId())
        .code(rec.getCode())
        .title(rec.getTitle())
        .path(rec.getPath())
        .build();
    }
}
