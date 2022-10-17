package com.clean.application.item.queries;

import com.clean.application.item.models.ItemAttributeValueModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.itm.Attribute;
import com.clean.domain.entity.itm.Item;
import com.clean.domain.entity.look.AttributeDataType;
import com.clean.persistence.item.AttributeRepository;
import com.clean.persistence.item.ItemRepository;
import com.clean.persistence.look.AttributeDataTypeRepository;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class FindItemAttributeValueQueryHandler implements IRequestHandler<FindItemAttributeValueQuery, List<ItemAttributeValueModel>> {
    private EntityManager manager;
    private AttributeRepository attributeRepository;
    private ItemRepository itemRepository;
    private AttributeDataTypeRepository dataTypeRepository;

    @Autowired
    FindItemAttributeValueQueryHandler(EntityManager manager,AttributeRepository attributeRepository,ItemRepository itemRepository,AttributeDataTypeRepository dataTypeRepository) {
        this.attributeRepository = attributeRepository;
        this.manager = manager;
        this.itemRepository = itemRepository;
        this.dataTypeRepository = dataTypeRepository;
    }

    @Override
    public List<ItemAttributeValueModel> handle(FindItemAttributeValueQuery request) {
        QueryEngine engine = new QueryEngine(manager);
        engine.from("Attribute", "A")
                .innerJoin("ItemAttribute", "IA", "A.id", "IA.attributeId")
                .innerJoin("ItemAttributeValue", "AV", "A.id", "AV.attributeId")
                .innerJoin("AttributeDataType", "DT", "A.dataTypeId", "DT.id");
        engine.where(WhereConditionType.NONE, "AV.itemDetailId", ConditionType.EQUAL, request.getItemDetailId());
        Item item= itemRepository.findItemByItemDetial(request.getItemDetailId());
        int itemId= (item == null ? request.getItemId() : item.getId());
        engine.where(WhereConditionType.AND, "IA.itemId", ConditionType.EQUAL, itemId );
        engine.addSelect("AV.id")
                .addSelect("A.id")
                .addSelect("AV.itemDetailId")
                .addSelect("AV.unitId")
                .addSelect("AV.value")
                .addSelect("A.name")
                .addSelect("DT.abbreviation")
                .addSelect("DT.dataTypeName");
        List<ItemAttributeValueModel> values = engine.execute(ItemAttributeValueModel.class);

        // get remain attributes

        List<Attribute> attributeList = attributeRepository.findAllByItemId(itemId);

        values.stream().map(e -> e.getAttributeId()).forEach(e -> {
            for (Attribute attribute : attributeList){
                if (attribute.getId() == e) {
                    attributeList.remove(attribute);
                    break;
                }
            }
        });

        for (Attribute attribute : attributeList) {
            ItemAttributeValueModel attributeValueModel = new ItemAttributeValueModel();
            attributeValueModel.setAttributeId(attribute.getId());
            attributeValueModel.setAttributeText(attribute.getName());
            AttributeDataType dataType = dataTypeRepository.findById(attribute.getDataTypeId()).get();
            attributeValueModel.setDataTypeAbbreviation(dataType.getAbbreviation());
            attributeValueModel.setDataTypeName(dataType.getDataTypeName());
            values.add(attributeValueModel);
        }

        return values;
    }
}
