package com.clean.application.processtracking.queries;

import java.util.List;

import com.clean.application.processtracking.models.StageModel;
import com.clean.common.mediator.core.IRequest;

import lombok.Data;

@Data
public class GetStageQuery implements IRequest<List<StageModel>> {
    private Boolean ActiveOnly;
}
