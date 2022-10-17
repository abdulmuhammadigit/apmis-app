package com.clean.application.security.queries;

import com.clean.application.security.models.EntityPermissionListModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class SearchRolePageEntityPermissionsQuery implements IRequest<List<EntityPermissionListModel>> {
    private Integer rolePageId;
}
