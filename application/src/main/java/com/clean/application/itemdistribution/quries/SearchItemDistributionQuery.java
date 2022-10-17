package com.clean.application.itemdistribution.quries;

import com.clean.application.itemdistribution.models.ItemDistributionSearchModel;
import com.clean.common.mediator.core.IRequest;
import lombok.*;

import java.util.List;

@Data
public class SearchItemDistributionQuery implements IRequest<List<ItemDistributionSearchModel>> {
    private String code;
    private String documentNumber;
}
