package com.clean.application.look.commands;

import java.util.List;
import java.util.stream.Collectors;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.itm.ItemDetail;
import com.clean.domain.entity.look.Unit;
import com.clean.persistence.item.ItemAttributeValueRepository;
import com.clean.persistence.item.ItemDetailRepository;
import com.clean.persistence.look.UnitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CreateUnitCommandHandler implements IRequestHandler<CreateUnitCommand,Unit> {
    private UnitRepository repository;
    private ItemAttributeValueRepository itemAttributeValueRepository;
    private ItemDetailRepository itemDetailRepository;
    @Autowired
    public CreateUnitCommandHandler(UnitRepository unitRepository , ItemAttributeValueRepository itemAttributeValueRepository,ItemDetailRepository itemDetailRepository){
        this.repository = unitRepository;
        this.itemAttributeValueRepository = itemAttributeValueRepository;
        this.itemDetailRepository = itemDetailRepository;
    }
    @Override
    public Unit handle(CreateUnitCommand request) {

        if(repository.existsByNameAndIdIsNot(request.getName(), request.getId())){
            throw new RuntimeException("واحد از قبل موجود میباشد!");
        }

        Unit unit = repository.findById(request.getId()).orElse(null);
        
        if(unit == null){
            unit = new Unit();
        }
        String oldName=unit.getName();
        unit.setName(request.getName());
        unit = repository.save(unit);
        String newName=unit.getName();
        if(oldName != null && !oldName.equals(newName)){
            List<Integer> itemDetailList= itemAttributeValueRepository.findAllByUnitId(unit.getId()).stream().map(e->e.getItemDetailId()).distinct().collect(Collectors.toList());
            itemDetailList.forEach(e->{
                ItemDetail itemDetail= itemDetailRepository.findById(e).get();
                String  detail = itemDetail.getDetail();
                itemDetail.setDetail(detail.replace(oldName,newName));
                itemDetailRepository.save(itemDetail);
            });
        }


        return unit;
    }
    
}
