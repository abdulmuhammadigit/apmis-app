package com.clean.application.security.queries;

import java.util.List;

import com.clean.application.security.models.RoleModel;
import com.clean.common.mediator.core.IRequest;

import lombok.Data;

@Data
public class SearchRolesQuery implements IRequest<List<RoleModel>> {
    private String name;
}
