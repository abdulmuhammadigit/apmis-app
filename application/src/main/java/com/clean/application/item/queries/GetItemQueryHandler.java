package com.clean.application.item.queries;

import com.clean.application.item.models.ItemModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.itm.Item;
import com.clean.persistence.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetItemQueryHandler implements IRequestHandler<GetItemQuery, List<ItemModel>> {
    private ItemRepository repository;
    @Autowired
    GetItemQueryHandler(ItemRepository repository){
        this.repository = repository;
    }

    @Override
    public List<ItemModel> handle(GetItemQuery request) {
        if(request.getCategoryId() != null){
            return repository.findAllByCategoryId(request.getCategoryId()).stream().map(ItemModel::map).collect(Collectors.toList());
        }
        return repository.findAll().stream().map(ItemModel::map).collect(Collectors.toList());
    }
}
