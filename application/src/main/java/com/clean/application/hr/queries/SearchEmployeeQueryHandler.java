package com.clean.application.hr.queries;

import com.clean.application.hr.models.EmployeeModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.hr.Employee;
import com.clean.persistence.hr.EmployeeRepository;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SearchEmployeeQueryHandler implements IRequestHandler<SearchEmployeeQuery,List<EmployeeModel>> {
    private EmployeeRepository repository;
    private EntityManager manager;
    @Autowired
    SearchEmployeeQueryHandler(EmployeeRepository repository, EntityManager manager){
        this.repository = repository;
        this.manager = manager;
    }
    @Override
    public List<EmployeeModel> handle(SearchEmployeeQuery request) {
        QueryEngine engine = new QueryEngine(this.manager);
        engine.from("Employee","E")
                .addSelect("E.id")
                .addSelect("E.code")
                .addSelect("E.name")
                .addSelect("E.lastName")
                .addSelect("E.fatherName")
                .addSelect("E.grandFatherName")
                .addSelect("E.position")
                .addSelect("E.orgUnitId")
                .addSelect("O.name")
                .addSelect("E.cardId")
                .innerJoin("OrgUnit","O","E.orgUnitId","O.id");
        if(request.getCode() != null && !request.getCode().isEmpty()){
            engine.where(WhereConditionType.NONE,"E.code",ConditionType.EQUAL,request.getCode());
        }else{
            if(request.getName() != null && !request.getName().isEmpty()){
                engine.where(WhereConditionType.NONE,"E.name",ConditionType.LIKE_ANY_POSITION,request.getName());
            }
            if(request.getFatherName() != null && !request.getFatherName().isEmpty()){
                engine.where(WhereConditionType.AND,"E.fatherName",ConditionType.LIKE_ANY_POSITION,request.getFatherName());
            }
            if(request.getLastName() != null && !request.getLastName().isEmpty()){
                engine.where(WhereConditionType.AND,"E.lastName",ConditionType.LIKE_ANY_POSITION,request.getLastName());
            }
        }

        List<EmployeeModel> models = engine.execute(EmployeeModel.class);
        return models;
    }
}
