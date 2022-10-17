package com.clean.application.item.queries;

import com.clean.application.item.models.ItemDetailModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.item.ItemDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetItemDetailQueryHandler implements IRequestHandler<GetItemDetailQuery, List<ItemDetailModel>> {

    private ItemDetailRepository repository;
    @Autowired
    GetItemDetailQueryHandler(ItemDetailRepository repository){
        this.repository = repository;
    }
    @Override
    public List<ItemDetailModel> handle(GetItemDetailQuery request) {
        if(request.getItemId()!= 0){
            return repository.findAllByItemId(request.getItemId()).stream().map(ItemDetailModel::map).collect(Collectors.toList());
        }
        return repository.findAll().stream().map(ItemDetailModel::map).collect(Collectors.toList());
    }

}
