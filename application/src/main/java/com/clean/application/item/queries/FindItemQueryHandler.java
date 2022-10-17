package com.clean.application.item.queries;

import com.clean.application.item.models.ItemModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.item.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindItemQueryHandler implements IRequestHandler<FindItemQuery, ItemModel> {
    private ItemRepository repository;
    @Autowired
    FindItemQueryHandler(ItemRepository repository){
        this.repository = repository;
    }
    @Override
    public ItemModel handle(FindItemQuery request) {
        return repository.findById(request.getId()).map(ItemModel::map).orElseThrow(() -> new RuntimeException("Not Found"));
    }
}
