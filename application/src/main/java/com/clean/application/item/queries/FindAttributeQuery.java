package com.clean.application.item.queries;

import com.clean.application.item.models.AttributeModel;
import com.clean.common.mediator.core.IRequest;
import com.clean.domain.entity.itm.Attribute;
import lombok.AllArgsConstructor;
import lombok.*;

@Data
@AllArgsConstructor
public class FindAttributeQuery implements IRequest<AttributeModel> {
    private int id;
}
