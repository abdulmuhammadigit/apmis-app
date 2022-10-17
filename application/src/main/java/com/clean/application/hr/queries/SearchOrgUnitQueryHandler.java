package com.clean.application.hr.queries;

import com.clean.application.hr.models.OrgUnitModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class SearchOrgUnitQueryHandler implements IRequestHandler<SearchOrgUnitQuery, List<OrgUnitModel>> {
   private EntityManager manager;

   @Autowired
    SearchOrgUnitQueryHandler(EntityManager manager){
       this.manager = manager;
   }
    @Override
    public List<OrgUnitModel> handle(SearchOrgUnitQuery request) {
        QueryEngine engine = new QueryEngine(this.manager);
        engine.from("OrgUnit","O")
              .innerJoin("OrgUnitType","OT","OT.id","O.orgUnitTypeId")
              .leftJoin("OrgUnit", "P", "O.parentId", "P.id");

        if (request.getName() !=null && !request.getName().isEmpty()){
            engine.where(WhereConditionType.AND,"O.name",ConditionType.LIKE_ANY_POSITION,request.getName());
        }
        engine.addSelect("O.id")
              .addSelect("O.orgUnitTypeId")
              .addSelect("O.name")
              .addSelect("O.parentId")
              .addSelect("OT.name")
              .addSelect("P.name");
        List<OrgUnitModel> models = engine.execute(OrgUnitModel.class);
        return models;
    }
}
