package com.clean.application.security.queries;

import com.clean.application.security.models.RolePagesModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class GetRolePageListQuery implements IRequest<List<RolePagesModel>> {
    private Integer roleId;
}
