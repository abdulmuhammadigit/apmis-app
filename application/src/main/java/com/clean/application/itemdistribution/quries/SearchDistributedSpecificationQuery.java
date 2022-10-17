package com.clean.application.itemdistribution.quries;

import com.clean.application.itemdistribution.models.ItemReceiptDistributedSpeceficationModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class SearchDistributedSpecificationQuery implements IRequest<List<ItemReceiptDistributedSpeceficationModel>> {
    private long itemDistributionId;
}
