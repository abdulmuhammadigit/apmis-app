package com.clean.application.item.commands;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.clean.application.item.models.ItemModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.itm.Item;
import com.clean.domain.entity.itm.ItemDetail;
import com.clean.persistence.item.ItemDetailRepository;
import com.clean.persistence.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateItemCommandHandler implements IRequestHandler<CreateItemCommand , ItemModel> {
    private ItemRepository repository;
    private ItemDetailRepository itemDetailRepository;
    @Autowired
    CreateItemCommandHandler(ItemRepository repository,ItemDetailRepository itemDetailRepository){
        this.repository = repository;
        this.itemDetailRepository = itemDetailRepository;
    }
    @Override
    public ItemModel handle(CreateItemCommand request) {
        //BRC:check for item name duplication
        if(repository.existsByNameAndIdIsNot(request.getName(),request.getId())){
            throw new RuntimeException("جنس از قبل موجود میباشد!");
        }

        Item item = repository.findById(request.getId()).orElse(null);

        if(item == null){
            item = new Item();
        }
        String oldName=item.getName();
        item.setCategoryId(request.getCategoryId());
        item.setConsumable(request.isConsumable());
        item.setName(request.getName());
        item.setUnitId(request.getUnitId());
        item = repository.save(item);
        String newName = item.getName();

        if(oldName != null && !oldName.equals(newName)){
            List<Integer> itemDetailList= itemDetailRepository.findAllByItemId(item.getId()).stream().map(e->e.getId()).distinct().collect(Collectors.toList());
            itemDetailList.forEach(e->{
                ItemDetail itemDetail= itemDetailRepository.findById(e).get();
                String  detail = itemDetail.getDetail();
                itemDetail.setDetail(detail.replace(oldName, newName));
                itemDetailRepository.save(itemDetail);
            });
        }
        
        return ItemModel.map(item);
    }
}
