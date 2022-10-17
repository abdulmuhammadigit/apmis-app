package com.clean.application.donor.queries;

import com.clean.application.donor.models.DonorModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class SearchDonorQueryHandler implements IRequestHandler<SearchDonorQuery , List<DonorModel>> {
    private EntityManager manager;
    @Autowired
    SearchDonorQueryHandler (EntityManager manager){
        this.manager = manager;
    }
    @Override
    public List<DonorModel> handle(SearchDonorQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        engine.from("Donor","d");
        if(request.getName() != null && !request.getName().isEmpty()){
            engine.where(WhereConditionType.NONE,"d.name", ConditionType.LIKE_ANY_POSITION,request.getName());

        }
        engine
                .addSelect("d.id")
                .addSelect("d.name")
                .addSelect("d.abbreviation")
        ;
        return engine.execute(DonorModel.class);
    }
}
