package com.clean.application.look.quries;

import com.clean.domain.entity.look.ItemReceiptType;
import com.clean.persistence.look.ItemReceiptTypeRepository;
import com.clean.common.mediator.core.IRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchItemReceiptTypeQueryHandler implements IRequestHandler<SearchItemReceiptTypeQuery,List<ItemReceiptType>> {
   private ItemReceiptTypeRepository repository;
   @Autowired
   SearchItemReceiptTypeQueryHandler(ItemReceiptTypeRepository repository){
       this.repository = repository;
   }

    @Override
    public List<ItemReceiptType> handle(SearchItemReceiptTypeQuery request) {
        return repository.findAll();
    }
}
