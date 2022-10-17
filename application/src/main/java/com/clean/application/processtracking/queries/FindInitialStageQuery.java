package com.clean.application.processtracking.queries;

import com.clean.common.mediator.core.IRequest;
import com.clean.domain.entity.prc.Stage;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FindInitialStageQuery implements IRequest<Stage> {
    private Integer entityId;
    private Integer classificationId;
}
