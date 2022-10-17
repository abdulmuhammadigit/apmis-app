package com.clean.application.look.quries;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.look.Category;
import com.clean.persistence.look.CategoryRepository;

public class FindCategoryQueryHandler implements IRequestHandler<FindCategoryQuery, Category> {
    private CategoryRepository categoryRepository;
    FindCategoryQueryHandler(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category handle(FindCategoryQuery request) {
        Category  category = categoryRepository.findById(request.getId()).orElseThrow( ()-> new RuntimeException("کتگوری دریافت نگردید!"));
        return category;
    }
}
