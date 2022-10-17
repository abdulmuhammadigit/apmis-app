package com.clean.application.itemreallocation.models;

import com.clean.domain.entity.stc.ItemReallocationDetail;
import com.clean.domain.entity.stc.ItemReallocationSpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemReallocationBoardModel {
    private long id;
    private long itemReallocationDetailId;
    private long itemDistributedSpecificationId;
    private String examinerBoard;
    private int statusId;
    private float price;
    private Integer toEmployeeId;
    private int boardStatusId;
    private String itemReallocationDetailText;
    private String statusText;
    private String toEmployeeText;

    public static ItemReallocationBoardModel map(ItemReallocationSpecification itemReallocationSpecification){
        ItemReallocationBoardModel model = new ItemReallocationBoardModel();
        model.setId(itemReallocationSpecification.getId());
        model.setItemReallocationDetailId(itemReallocationSpecification.getItemReallocationDetailId());
        model.setItemDistributedSpecificationId(itemReallocationSpecification.getItemDistributedSpecificationId());
        model.setExaminerBoard(itemReallocationSpecification.getExaminerBoard());
        model.setStatusId(itemReallocationSpecification.getStatusId());
        model.setPrice(itemReallocationSpecification.getPrice());
        model.setToEmployeeId(itemReallocationSpecification.getToEmployeeId());
        model.setBoardStatusId(itemReallocationSpecification.getBoardStatusId());
        return model;
    }
}
