package com.clean.application.hr.queries;

import com.clean.application.hr.models.OrgUnitModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

@Data
public class FindOrgUnitQuery implements IRequest<OrgUnitModel> {
    public int id;
}
