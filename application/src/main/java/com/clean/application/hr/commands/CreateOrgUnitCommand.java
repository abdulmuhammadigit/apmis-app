package com.clean.application.hr.commands;

import com.clean.application.hr.models.OrgUnitModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

@Data
public class CreateOrgUnitCommand implements IRequest<OrgUnitModel> {
    private int id;
    private short orgUnitTypeId;
    private String name;
    private Integer parentId;
    private String sorter;
}
