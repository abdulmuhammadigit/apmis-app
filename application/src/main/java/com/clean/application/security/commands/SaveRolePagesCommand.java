package com.clean.application.security.commands;

import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class SaveRolePagesCommand implements IRequest<Boolean> {
    private Integer roleId;
    private List<Integer> pageIds;
}
