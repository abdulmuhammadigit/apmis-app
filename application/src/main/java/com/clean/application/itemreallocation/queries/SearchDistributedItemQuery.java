package com.clean.application.itemreallocation.queries;

import java.util.List;

import com.clean.application.itemdistribution.models.ItemReceiptDistributedSpeceficationModel;
import com.clean.application.itemreallocation.models.ItemDistributedSpecification;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDistributedItemQuery implements IRequest<List<ItemDistributedSpecification>>  {
    private long itemDistributionDetailId;
}
