
package com.clean.application.itemdistribution.quries;

import com.clean.application.itemdistribution.models.ItemDistributionPrintModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrintItemDistributionQuery implements IRequest<ItemDistributionPrintModel> {
    private long id;
}
