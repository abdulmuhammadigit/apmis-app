package com.clean.application.itemrequest.commands;

import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessItemRequestCheckCommand implements IRequest<Boolean> {
    private long recordId;
    private int stageId;
    private String businessRule;
}
