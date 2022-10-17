package com.clean.application.hr.queries;

import com.clean.common.mediator.core.IRequest;
import com.clean.domain.entity.hr.OrgUnit;
import lombok.Data;

import java.util.List;
@Data
public class GetOrgUnitQuery implements IRequest<List<OrgUnit>> {
}
