package com.clean.application.look.quries;

import com.clean.common.mediator.core.IRequest;
import com.clean.domain.entity.look.Category;
import lombok.Data;

@Data
public class FindCategoryQuery implements IRequest<Category> {
    private short id;
}
