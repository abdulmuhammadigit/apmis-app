package com.clean.application.itemreallocation.queries;

import com.clean.application.itemreallocation.models.ItemReallocationSearchModel;
import com.clean.application.services.UserService;
import com.clean.common.constant.EntityIdMapped;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.sec.UserStageRepository;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class SearchItemReallocationQueryHandler implements IRequestHandler<SearchItemReallocationQuery, List<ItemReallocationSearchModel>> {
   private EntityManager manager;
   private UserService userService;
   private UserStageRepository userStageRepository;
   @Autowired
    SearchItemReallocationQueryHandler(EntityManager manager , UserService userService, UserStageRepository userStageRepository)
   {
     this.manager = manager;
     this.userService = userService;
     this.userStageRepository = userStageRepository;
   }
    @Override
    public List<ItemReallocationSearchModel> handle(SearchItemReallocationQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        engine.from("ItemReallocation","IRE");
        if(request.getCode() !=null && !request.getCode().isEmpty()){
            engine.where(WhereConditionType.NONE,"IRE.code", ConditionType.EQUAL,request.getCode());
        }
        if(request.getDocumentNumber() !=null && !request.getDocumentNumber().isEmpty()){
            engine.where(WhereConditionType.AND,"IRE.documentNumber",ConditionType.EQUAL,request.getDocumentNumber());
        }

        List<Integer> stageList= userStageRepository.findAllByUserIdAndEntityId(userService.getUserId(), EntityIdMapped.ItemReallocation).stream().map(e->e.getStageId()).collect(Collectors.toList());
        engine.where(WhereConditionType.AND, "IRE.stageId", ConditionType.IN, stageList);

        engine
              .addSelect("IRE.id")
              .addSelect("IRE.code")
              .addSelect("IRE.documentNumber")
              .addSelect("IRE.date")
              .addSelect("IRE.description")
        ;
        return engine.execute(ItemReallocationSearchModel.class);
    }
}
