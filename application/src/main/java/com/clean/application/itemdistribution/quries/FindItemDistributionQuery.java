package com.clean.application.itemdistribution.quries;

import com.clean.application.itemdistribution.models.ItemDistributionModel;
import com.clean.common.mediator.core.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindItemDistributionQuery implements IRequest<ItemDistributionModel> {
    private long id;
}
