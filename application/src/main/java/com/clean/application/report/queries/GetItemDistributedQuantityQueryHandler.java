package com.clean.application.report.queries;
import com.clean.application.report.models.ItemDistributedQuantityModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.itemdistribution.ItemDistributedRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class GetItemDistributedQuantityQueryHandler implements IRequestHandler<GetItemDistributedQuantityQuery, List<ItemDistributedQuantityModel>> {

    private EntityManager manager;
    private ItemDistributedRepository itemDistributedRepository;

    @Autowired
    public GetItemDistributedQuantityQueryHandler(EntityManager manager, ItemDistributedRepository itemDistributedRepository) {
        this.manager = manager;
        this.itemDistributedRepository = itemDistributedRepository;
    }

    @Override
    public List<ItemDistributedQuantityModel> handle(GetItemDistributedQuantityQuery request) {
        List<ItemDistributedQuantityModel> list = new ArrayList<>();
        String condition1="";
        String condition2="";
        String condition3="";


        if(request.getOrgUnitId()!= 0){
            condition1 = " and O.id =:orgUnitId ";
        }
        if(request.isConsumable()){
            condition2 = " and I.consumable =:consumable ";
        }
        if(request.getStartDate() != null && request.getEndDate() != null){
            condition3 = " and IDN.date between :startDate and :endDate ";
        }
        Query query =
                manager.createQuery("SELECT" +
                        " O.name ," +
                        " sum(IDD.quantity) as totalItem ," +
                        " O.id " +
                        " from ItemDistributionDetail IDD " +
                        " join ItemDistributed ID on IDD.id=ID.itemDistributionDetailId " +
                        " join ItemDistribution IDN on IDD.itemDistributionId=IDN.id" +
                        " join ItemRequest R on IDN.itemRequestId=R.id" +
                        " join Employee E on R.employeeId=E.id" +
                        " join OrgUnit O on E.orgUnitId=O.id" +
                        " join ItemDetail IDL on IDD.itemDetailId=IDL.id" +
                        " join Item I on IDL.itemId=I.id" +
                        " where IDD.distributed=true "+
                         condition1 + condition2 + condition3 +
                        " group by O.id");

        if(request.getOrgUnitId()!= 0){
            query.setParameter("orgUnitId", request.getOrgUnitId());
        }

        if(request.isConsumable()){
            query.setParameter("consumable", request.isConsumable());
        }
        if(request.getStartDate() != null || request.getEndDate() != null){
            query.setParameter("startDate", request.getStartDate());
            query.setParameter("endDate", request.getEndDate());
        }
        for (Object cur: query.getResultList()) {
            Object[] result = (Object[]) cur;
            ItemDistributedQuantityModel model = new ItemDistributedQuantityModel();
            model.setOrgUnitName((String)result[0]);
            model.setCount(Integer.parseInt(result[1].toString()));
            model.setId(Long.parseLong(result[2].toString()));
            list.add(model);
        }

return list;

    }
}
