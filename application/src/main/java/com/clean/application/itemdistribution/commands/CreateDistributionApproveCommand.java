package com.clean.application.itemdistribution.commands;

import com.clean.common.mediator.core.IRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDistributionApproveCommand implements IRequest<Boolean> {
    long itemDistributionId;
}
