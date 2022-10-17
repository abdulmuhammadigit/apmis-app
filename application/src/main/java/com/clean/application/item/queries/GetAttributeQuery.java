package com.clean.application.item.queries;

import com.clean.application.item.models.AttributeModel;
import com.clean.common.mediator.core.IRequest;
import com.clean.domain.entity.itm.Attribute;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
public class GetAttributeQuery implements IRequest<List<AttributeModel>> {
    private short categoryId;
}
