package com.clean.application.look.quries;

import com.clean.application.look.models.CategoryModel;
import com.clean.common.mediator.core.IRequest;
import com.clean.domain.entity.look.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

public class SearchCategoryQuery implements IRequest<List<CategoryModel>> {
    private String name;
}
