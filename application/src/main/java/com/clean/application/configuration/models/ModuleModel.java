package com.clean.application.configuration.models;
import com.clean.domain.entity.conf.Modules;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleModel {
 String title;
 int id;
 String icon;
 String page;
 String description;
 int sorter;
 List<PageModel> menus;

    public static ModuleModel Map(Modules entity){
	  return ModuleModel.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .description(entity.getDescription())
            .icon(entity.getIcon())
            .page(entity.getPage())
            .sorter(entity.getSorter())
        .build();
      }
      
      public static ModuleModel FullMap(Modules entity){
        return ModuleModel.builder()
              .id(entity.getId())
              .title(entity.getTitle())
              .description(entity.getDescription())
              .icon(entity.getIcon())
              .page(entity.getPage())
              .sorter(entity.getSorter())
              .menus(entity.getMenus().stream().map(PageModel::FullMap).collect(Collectors.toList()))
          .build();
        }
 } 
