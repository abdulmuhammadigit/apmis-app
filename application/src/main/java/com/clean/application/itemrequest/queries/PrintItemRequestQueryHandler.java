package com.clean.application.itemrequest.queries;

import javax.persistence.EntityManager;

import com.clean.application.itemrequest.models.ItemRequestPrintModel;
import com.clean.common.mediator.core.IRequestHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PrintItemRequestQueryHandler implements IRequestHandler<PringItemRequestQuery, ItemRequestPrintModel> {
    private EntityManager manager;
    @Autowired
    public PrintItemRequestQueryHandler(EntityManager manager) {
        this.manager = manager;
    }
    @Override
    public ItemRequestPrintModel handle(PringItemRequestQuery request) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
