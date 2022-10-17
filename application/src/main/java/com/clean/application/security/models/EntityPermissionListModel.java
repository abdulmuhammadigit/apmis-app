package com.clean.application.security.models;

import lombok.Data;

import java.util.List;

@Data
public class EntityPermissionListModel {
    private Integer Id;
    private String Title;
    private List<PageEntityPermissionModel> permissions;
}
