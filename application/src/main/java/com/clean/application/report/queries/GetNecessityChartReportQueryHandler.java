package com.clean.application.report.queries;
import com.clean.application.report.models.NecessityChartReportModel;
import com.clean.common.mediator.core.IRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class GetNecessityChartReportQueryHandler implements IRequestHandler <GetNecessityChartReportQuery, List<NecessityChartReportModel>> {
private EntityManager manager;

    @Autowired
    public GetNecessityChartReportQueryHandler(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<NecessityChartReportModel> handle(GetNecessityChartReportQuery request) {
        List<NecessityChartReportModel> list = new ArrayList<>();
        String condition = "";
        if (request.getOrgUnitId() != 0 && request.getFiscalYearId()!= 0 ) {
            condition = " where NC.orgUnitId =:orgUnit and NC.fiscalYearId =:fiscalYear ";
        }



        Query query =
                manager.createQuery("SELECT" +
                        " NC.id, ID.detail ," +
                        " sum(NCD.requestedQuantity) as requestedQuantity , " +
                        " sum(NCD.commissionDecision ) as commissionDecisionQuantity ," +
                        " sum(IRD.remain ) as remain " +
                        " from NecessityChart NC " +
                        " join NecessityChartDetail NCD on NC.id=NCD.neccessityChartId " +
                        " join ItemDetail ID on NCD.itemDetailId=ID.id " +
                        " left join ItemReceiptDetail IRD on ID.id=IRD.itemDetailId " +
                        condition +
                        " group by NC.id, ID.detail");

        if (request.getOrgUnitId() != 0 && request.getFiscalYearId()!= 0) {
            query.setParameter("orgUnit", request.getOrgUnitId());
            query.setParameter("fiscalYear", request.getFiscalYearId());

        }

        for (Object cur : query.getResultList()) {
            Object[] result = (Object[]) cur;
            NecessityChartReportModel model = new NecessityChartReportModel();
            model.setId(Long.parseLong(result[0].toString()));
            model.setDetail((String) result[1]);
            model.setRequestedQuantity(Integer.parseInt(result[2].toString()));
            model.setCommissionDecisionQuantity(Integer.parseInt(result[3].toString()));
            model.setRemain(Integer.parseInt(result[4].toString()));
            list.add(model);
        }

        return list;

    }
    }
