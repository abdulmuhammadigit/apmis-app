package com.clean.application.processtracking.queries;

import java.util.List;

import com.clean.application.processtracking.models.WorkFlowModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchWorkFlowQuery implements IRequest<List<WorkFlowModel>> {
    private String name;
}
