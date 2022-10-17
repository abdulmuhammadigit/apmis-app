package com.clean.application.item.queries;

import com.clean.domain.entity.look.Category;
import com.clean.persistence.look.CategoryRepository;
import com.clean.common.mediator.core.IRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchCategoryQueryHandler implements IRequestHandler<SearchCategoryQuery, List<Category>> {
    private CategoryRepository repository;
    @Autowired
    SearchCategoryQueryHandler(CategoryRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Category> handle(SearchCategoryQuery request) {
        return repository.findAll();
    }
}
