package com.clean.application.item.queries;

import com.clean.application.item.models.AttributeModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.item.AttributeRepository;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SearchAttributeQueryHandler implements IRequestHandler<SearchAttributeQuery , List<AttributeModel>> {
    EntityManager manager;
    @Autowired
    SearchAttributeQueryHandler(EntityManager manager){
        this.manager = manager;
    }
    @Override
    public List<AttributeModel> handle(SearchAttributeQuery request) {
            QueryEngine queryEngine = new QueryEngine(manager);

            queryEngine.from("Attribute","A");
            queryEngine.innerJoin("Category","C","A.categoryId","C.id")
                    .innerJoin("AttributeDataType","D","A.dataTypeId","D.id");

            if(request.getCategoryId() != 0){
                queryEngine.where(WhereConditionType.NONE ,"A.categoryId", ConditionType.EQUAL,request.getCategoryId());
            }
            if(request.getName() != null && !request.getName().isEmpty()){
                queryEngine.where(WhereConditionType.AND, "A.name",ConditionType.LIKE_ANY_POSITION,request.getName());
            }

            queryEngine.addSelect("A.id").addSelect("A.categoryId").addSelect("A.name").
                    addSelect("A.dataTypeId").addSelect("C.name").
                    addSelect("D.dataTypeName");

            queryEngine.orderByAsc("A.categoryId").orderByAsc("A.name");

            return queryEngine.execute(AttributeModel.class);
    }
}
