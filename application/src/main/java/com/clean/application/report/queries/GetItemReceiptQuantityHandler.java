package com.clean.application.report.queries;

import com.clean.application.report.models.ItemQuantityModel;
import com.clean.application.report.models.ItemReceiptQuantityModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.itemreceipt.ItemReceiptDetailRepository;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GetItemReceiptQuantityHandler implements IRequestHandler<GetItemReceiptQuantityQuery,List< ItemReceiptQuantityModel>> {

    private EntityManager manager;
    private ItemReceiptDetailRepository itemReceiptDetailRepository;
    @Autowired
    GetItemReceiptQuantityHandler( EntityManager manager, ItemReceiptDetailRepository itemReceiptDetailRepository) {
        this.manager = manager;
        this.itemReceiptDetailRepository = itemReceiptDetailRepository;
    }

    @Override
    public List<ItemReceiptQuantityModel> handle(GetItemReceiptQuantityQuery request) {
        List<ItemReceiptQuantityModel> list = new ArrayList<>();
        String condition = "";

        if(request.getLocalProduct() != null && request.getStartDate() != null && request.getEndDate() != null){
            condition = " where RD.localProduct = :localProduct and IR.receiveDate between :startDate and :endDate ";
        }
        else if (request.getLocalProduct() != null  ){
            condition = "where RD.localProduct = :localProduct";
        }
        else if ( request.getStartDate() != null && request.getEndDate() != null){
            condition = " where IR.receiveDate between :startDate and :endDate ";
        }



        Query query =
                manager.createQuery("SELECT" +
                        " RT.name ," +
                        " count(distinct RD.itemReceiptId) as totalItemReceipt , " +
                        " sum(RD.quantity) as totalItem , " +
                        " sum( RD.price*RD.quantity) as totalPrice ," +
                        " IR.itemReceiptTypeId " +
                        " from ItemReceiptDetail RD " +
                        " join ItemReceipt IR on IR.id=RD.itemReceiptId " +
                        " join ItemReceiptType RT on RT.id=IR.itemReceiptTypeId " +
                        condition +
                        " group by IR.itemReceiptTypeId , RT.name");


        if(request.getLocalProduct() != null && request.getStartDate() != null && request.getEndDate() != null){
            query.setParameter("localProduct", request.getLocalProduct());
            query.setParameter("startDate", request.getStartDate());
            query.setParameter("endDate", request.getEndDate());
        }
        else if(request.getLocalProduct() != null){
            query.setParameter("localProduct", request.getLocalProduct());
        }
        else if(request.getStartDate() != null && request.getEndDate() != null){
            query.setParameter("startDate", request.getStartDate());
            query.setParameter("endDate", request.getEndDate());
        }




        for (Object cur: query.getResultList()) {
            Object[] result = (Object[]) cur;

            ItemReceiptQuantityModel model = new ItemReceiptQuantityModel();

            model.setName((String)result[0]);
            model.setItemReceiptCount(Integer.parseInt(result[1].toString()));
            model.setItemCount(Integer.parseInt(result[2].toString()));
            model.setPriceTotal(Double.parseDouble(result[3].toString()));
            model.setId(Long.parseLong(result[4].toString()));

            list.add(model);
        }
return list;
    }
}
