package com.clean.application.processtracking.queries;

import com.clean.application.processtracking.models.EntityClassificationModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindEntityClassificationQuery implements IRequest<EntityClassificationModel> {
    private int id;
}
