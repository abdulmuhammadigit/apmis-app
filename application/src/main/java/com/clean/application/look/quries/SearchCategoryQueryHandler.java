package com.clean.application.look.quries;

import com.clean.application.look.models.CategoryModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.look.Category;
import com.clean.domain.entity.sec.Entity;
import com.clean.persistence.look.CategoryRepository;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class SearchCategoryQueryHandler implements IRequestHandler<SearchCategoryQuery,List<CategoryModel>> {
    EntityManager manager;
    @Autowired
    SearchCategoryQueryHandler(EntityManager manager){
        this.manager = manager;
    }


    @Override
    public List<CategoryModel> handle(SearchCategoryQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        engine.from("Category","C")
                .addSelect("C.id")
                .addSelect("C.name");
        if(request.getName() != null && request.getName() != ""){
            engine.where(WhereConditionType.NONE,"C.name", ConditionType.LIKE_ANY_POSITION,request.getName());
        }
        List<CategoryModel> list= engine.execute(CategoryModel.class);
        return  list;
    }
}
