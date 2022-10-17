package com.clean.application.itemdistribution.commands;

import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteDistributionDetailCommand implements IRequest<Boolean> {
    private long id;
}
