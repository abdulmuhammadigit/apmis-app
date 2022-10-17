package com.clean.application.security.queries;

import com.clean.application.security.models.RolePageEntityPermissionModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class SearchRolePagePermissionsQuery implements IRequest<List<RolePageEntityPermissionModel>> {
    private Integer rolePageId;
    private Integer entityId;
}
