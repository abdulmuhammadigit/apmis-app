package com.clean.application.configuration.models;
import com.clean.domain.entity.conf.Pages;
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

public class PageModel {
 private String title;
 private String icon;
 private String page;
 private String description;
 private String abbr;
 private List<PageModel> submenu;
 private String toggle;
 private boolean root;
 private String translate;
 private String bullet;
 private String alignment;
 private String permission;


 public static PageModel Map(Pages entity){
	  return PageModel.builder()
  		.title(entity.getTitle())
        .description(entity.getDescription())
        .icon(entity.getIcon())
        .page(entity.getPage())
        .abbr(entity.getAbbr())
        .toggle(entity.getToggle())
        .translate(entity.getTranslate())
        .bullet(entity.getBullet())
        .alignment(entity.getAlignment())
        .permission(entity.getPermission())
        .root(entity.isRoot())
        .build();
  	}

 
 public static PageModel FullMap(Pages entity){
    return PageModel.builder()
        .title(entity.getTitle())
      .description(entity.getDescription())
      .icon(entity.getIcon())
      .page(entity.getPage())
      .abbr(entity.getAbbr())
      .toggle(entity.getToggle())
      .translate(entity.getTranslate())
      .bullet(entity.getBullet())
      .alignment(entity.getAlignment())
      .permission(entity.getPermission())
      .root(entity.isRoot())
      .submenu(entity.getSubmenu().stream().map(PageModel::FullMap).collect(Collectors.toList()))
      .build();
    }
}
