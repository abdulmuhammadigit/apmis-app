package com.clean.application.itemreceipt.quries;

import com.clean.application.itemreceipt.models.ItemReceiptSearchModel;
import com.clean.application.services.UserService;
import com.clean.common.constant.EntityIdMapped;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.sec.UserStageRepository;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class SearchItemReceiptQueryHandler implements IRequestHandler<SearchItemReceiptQuery, List<ItemReceiptSearchModel>> {

    private EntityManager entityManager;
    private UserService userService;
    private UserStageRepository userStageRepository;
    @Autowired
    public SearchItemReceiptQueryHandler(EntityManager entityManager , UserService userService , UserStageRepository userStageRepository){
        this.entityManager=entityManager;
        this.userService = userService;
        this.userStageRepository = userStageRepository;
    }

    @Override
    public List<ItemReceiptSearchModel> handle(SearchItemReceiptQuery request) {

            QueryEngine queryEngine = new QueryEngine(entityManager);
            queryEngine.from("ItemReceipt","M7")
                    .innerJoin("StockKeeper","S","M7.stockKeeperId","S.id")
                    .innerJoin("Employee","E","E.id","S.employeeId")
                    .innerJoin("ItemReceiptType","T","T.id","M7.itemReceiptTypeId");
 
            if(request.getCode() != null && !request.getCode().isEmpty()){
                queryEngine.where(WhereConditionType.NONE, "M7.code", ConditionType.EQUAL,request.getCode());
            }

            if(request.getBillNumber() != null && !request.getBillNumber().isEmpty()){
                queryEngine.where(WhereConditionType.AND, "M7.billNumber", ConditionType.EQUAL,request.getBillNumber());
            }

            if(request.getDocumentNumber() != null && !request.getDocumentNumber().isEmpty()){
                queryEngine.where(WhereConditionType.AND, "M7.billNumber", ConditionType.EQUAL,request.getDocumentNumber());
            }

            
        List<Integer> stageList= userStageRepository.findAllByUserIdAndEntityId(userService.getUserId(), EntityIdMapped.ItemReceipt).stream().map(e->e.getStageId()).collect(Collectors.toList());
        queryEngine.where(WhereConditionType.AND, "M7.stageId", ConditionType.IN, stageList);

            queryEngine.addSelect("M7.id");
            queryEngine.addSelect("M7.code");
            queryEngine.addSelect("T.name");
            queryEngine.addSelect("M7.itemReceiptTypeId");
            queryEngine.addSelect("M7.receiveDate");
            queryEngine.addSelect("M7.billNumber");
            queryEngine.addSelect("E.name"); 
            queryEngine.addSelect("M7.stockKeeperId");
            queryEngine.addSelect("M7.description");
            queryEngine.addSelect("E.lastName");
            queryEngine.addSelect("E.fatherName");
            return queryEngine.execute(ItemReceiptSearchModel.class);

    }
}
