package com.clean.application.look.commands;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.look.Category;
import com.clean.persistence.look.CategoryRepository;

public class CreateCategoryCommandHandler implements IRequestHandler<CreateCategoryCommand, Category> {
    private CategoryRepository categoryRepository;
    CreateCategoryCommandHandler(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category handle(CreateCategoryCommand request) {
        //BRC:check for category exist
        if(categoryRepository.existsByName(request.getName())){
            throw new RuntimeException("کتگوری از قبل وجود دارد!");
        }
        Category category = categoryRepository.findById(request.getId()).orElse(null);
        if (category == null) {
            category = new Category();
        }
        category.setName(request.getName());
        return categoryRepository.save(category);
    }
}
