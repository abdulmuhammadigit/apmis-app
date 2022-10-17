package com.clean.application.item.commands;

import com.clean.application.item.models.ItemAttributeModel;
import com.clean.application.item.queries.SearchItemAttributeQuery;
import com.clean.common.mediator.core.IMediator;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.item.ItemAttributeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public class DeleteItemAttributeCommandHandler implements IRequestHandler<DeleteItemAttributeCommand,Boolean> {
    private ItemAttributeRespository respository;
    @Autowired
    DeleteItemAttributeCommandHandler(ItemAttributeRespository respository){
        this.respository = respository;
    }
    @Override
    public Boolean handle(DeleteItemAttributeCommand request) {
        respository.deleteById(request.getId());
        return true;
    }
}
