package com.clean.application.itemrequest.models;

import com.clean.domain.entity.stc.ItemRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ItemRequestModel {
    private long id;
    private String code;
    private boolean completed;
    private Date date;
    private String description;
    private String documentNumber;
    private int employeeId;
    private String department;
    private int stageId;
    private  String employeeText;
    private String orgUnitText;
    private List<ItemRequestDetailModel> detailModelList;

    public static ItemRequestModel map(ItemRequest itemRequest)
    {
        ItemRequestModel model = new ItemRequestModel();
        model.setId(itemRequest.getId());
        model.setCompleted(itemRequest.isCompleted());
        model.setCode(itemRequest.getCode());
        model.setDate(itemRequest.getDate());
        model.setDescription(itemRequest.getDescription());
        model.setDocumentNumber(itemRequest.getDocumentNumber());
        model.setEmployeeId(itemRequest.getEmployeeId());
        model.setStageId(itemRequest.getStageId());
        model.setDepartment(itemRequest.getDepartment());
        return model;
    }
}
