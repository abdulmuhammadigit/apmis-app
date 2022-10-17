package com.clean.application.itemreallocation.models;

import com.clean.domain.entity.stc.ItemReallocation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ItemReallocationModel {
    private long id;
    private String code;
    private String documentNumber;
    private Date date;
    private String description;
    private int employeeId;
    private String employeeText;
    private int stageId;
    private List<ItemReallocationDetailModel> itemReallocationDetailModels;

    public  static ItemReallocationModel map(ItemReallocation itemReallocation){
        ItemReallocationModel model = new ItemReallocationModel();
        model.setId(itemReallocation.getId());
        model.setCode(itemReallocation.getCode());
        model.setDocumentNumber(itemReallocation.getDocumentNumber());
        model.setDate(itemReallocation.getDate());
        model.setDescription(itemReallocation.getDescription());
        model.setStageId(itemReallocation.getStageId());
        return  model;
    }

}
