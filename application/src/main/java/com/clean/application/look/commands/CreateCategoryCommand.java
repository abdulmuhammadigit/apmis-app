package com.clean.application.look.commands;

import com.clean.common.mediator.core.IRequest;
import com.clean.domain.entity.look.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCategoryCommand implements IRequest<Category> {
    private short id;
    private String name;
}
