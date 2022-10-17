
package com.clean.application.processtracking.commands;

import com.clean.application.processtracking.models.InitialHistoryCommandResult;
import com.clean.common.mediator.core.IRequest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateInitialHistoryCommand implements IRequest<InitialHistoryCommandResult> {
    private Long recordId;
    private Integer entityId;
    private Integer classificationId;
}
