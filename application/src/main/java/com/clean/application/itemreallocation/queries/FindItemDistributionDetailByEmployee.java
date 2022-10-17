package com.clean.application.itemreallocation.queries;

import com.clean.application.itemdistribution.models.ItemDistributionDetailModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class FindItemDistributionDetailByEmployee implements IRequest<List<ItemDistributionDetailModel>> {
    private int employeeId;
}
