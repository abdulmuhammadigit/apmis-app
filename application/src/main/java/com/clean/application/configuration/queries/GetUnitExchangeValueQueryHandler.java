package com.clean.application.configuration.queries;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.conf.UnitExchange;
import com.clean.domain.entity.itm.Item;
import com.clean.persistence.configuration.UnitExchangeRepository;
import com.clean.persistence.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUnitExchangeValueQueryHandler implements IRequestHandler<GetUnitExchangeValueQuery,Short> {
   private ItemRepository itemRepository;
   private UnitExchangeRepository unitExchangeRepository;
   @Autowired
   GetUnitExchangeValueQueryHandler(ItemRepository itemRepository,UnitExchangeRepository unitExchangeRepository){
       this.itemRepository = itemRepository;
       this.unitExchangeRepository = unitExchangeRepository;
   }
    @Override
    public Short handle(GetUnitExchangeValueQuery request) {

        Item item =  itemRepository.findItemByItemDetial(request.getItemDetailId());
        List<UnitExchange> unitExchange= unitExchangeRepository.findAllByUnitIdAndToUnitId(request.getUnitId(),item.getUnitId()).stream().collect(Collectors.toList());
 
        if(unitExchange.size() > 1){ 
            unitExchange = unitExchange.stream().filter(e -> e.getItemId() != null && e.getItemId() == item.getId()).collect(Collectors.toList());
              
            if(unitExchange.size()>1 || unitExchange.size() < 1){
                throw new RuntimeException("تبدیل واحد برای جنس انتخاب شده دریافت نگردید!");
            }
        } 

        if( (unitExchange == null || unitExchange.size() == 0) && request.getUnitId() != item.getUnitId()){
            throw new RuntimeException("اصل واحد جنس و واحد درخواست شده یکسان نمیباشد!");
        }

        if(unitExchange != null && unitExchange.size() != 0){
            return (short) (unitExchange.get(0).getQuantity() * request.getQuantity());
        }
        return 0;
    }
}
