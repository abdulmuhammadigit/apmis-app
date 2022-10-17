package com.clean.application.security.models;

import com.clean.domain.entity.conf.Pages;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageModel {
    private Integer id;
    private String title;
    private Integer parentId;
    private String sorter;
    private Integer moduleId;

    public static PageModel Map(Pages entity) {
        return PageModel.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .parentId(entity.getParentId())
                .sorter(entity.getSorter())
                .moduleId(entity.getModuleId())
                .build();
    }
}
