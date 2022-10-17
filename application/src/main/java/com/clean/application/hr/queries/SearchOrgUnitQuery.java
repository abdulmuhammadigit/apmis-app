package com.clean.application.hr.queries;

import com.clean.application.hr.models.OrgUnitModel;
import com.clean.common.mediator.core.IRequest;

import lombok.Data;

import java.util.List;

@Data
public class SearchOrgUnitQuery implements IRequest<List<OrgUnitModel>> {
    private String name;
}
