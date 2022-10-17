package com.clean.application.security.commands;

import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class SaveRolePageEntityPermissionCommand implements IRequest<Boolean> {
    private Integer rolePageId;
    private Integer entityId;
    private List<Integer> permissionIds;
}
