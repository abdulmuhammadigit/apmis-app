package com.clean.application.hr.models;

import com.clean.domain.entity.hr.OrgUnit;
import com.google.gson.internal.$Gson$Preconditions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrgUnitModel {
    private int id;
    private int orgUnitTypeId;
    private String name;
    private Integer ParentId;
    private String orgUnitTypeName;
    private String ParentText;
    public static OrgUnitModel map(OrgUnit orgUnit){
        OrgUnitModel model = new OrgUnitModel();
        model.setId(orgUnit.getId());
        model.setOrgUnitTypeId(orgUnit.getOrgUnitTypeId());
        model.setName(orgUnit.getName());
        model.setParentId(orgUnit.getParentId());
        return model;
    }

}


