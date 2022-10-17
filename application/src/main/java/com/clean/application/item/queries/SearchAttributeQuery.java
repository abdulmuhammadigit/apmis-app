package com.clean.application.item.queries;

import com.clean.application.item.models.AttributeModel;
import com.clean.common.mediator.core.IRequest;
import lombok.*;

import java.util.List;
@Data
public class SearchAttributeQuery implements IRequest<List<AttributeModel>> {
    private short categoryId;
    private String name;
}
