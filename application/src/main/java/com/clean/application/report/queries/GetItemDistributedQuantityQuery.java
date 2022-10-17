package com.clean.application.report.queries;

import com.clean.application.report.models.ItemDistributedQuantityModel;
import com.clean.application.report.models.ItemQuantityModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class GetItemDistributedQuantityQuery  implements IRequest<List<ItemDistributedQuantityModel>> {

    private boolean consumable;
     private Date startDate;
    private Date endDate;
    private int orgUnitId;
}
