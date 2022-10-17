package com.clean.application.security.queries;

import com.clean.application.security.models.RoleModel;
import com.clean.common.mediator.core.IRequest;

import lombok.Data;

@Data
public class FindRoleQuery implements IRequest<RoleModel> {
    private Integer id;
}
