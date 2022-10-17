package com.clean.application.hr.queries;

import com.clean.application.hr.models.OrgUnitModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.hr.OrgUnit;
import com.clean.domain.entity.look.OrgUnitType;
import com.clean.persistence.hr.OrgUnitRepository;
import com.clean.persistence.look.OrgUnitTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FindOrgUnitQueryHandler implements IRequestHandler<FindOrgUnitQuery , OrgUnitModel> {
    private OrgUnitRepository orgUnitRepository;
    private OrgUnitTypeRepository orgUnitTypeRepository;
    @Autowired
    FindOrgUnitQueryHandler(OrgUnitRepository orgUnitRepository,OrgUnitTypeRepository orgUnitTypeRepository){
        this.orgUnitRepository = orgUnitRepository;
        this.orgUnitTypeRepository = orgUnitTypeRepository;
    }
    @Override
    public OrgUnitModel handle(FindOrgUnitQuery request) {
        OrgUnit orgUnit = orgUnitRepository.findByIdAndParentId(request.getId());
        Optional<OrgUnitType> orgUnitType = orgUnitTypeRepository.findById(orgUnit.getOrgUnitTypeId());
        OrgUnitModel orgUnitModel = OrgUnitModel.map(orgUnit);
        orgUnitModel.setOrgUnitTypeName(orgUnitType.get().getName());
        if(orgUnit.getParentId()!=null && orgUnit.getParentId()!=0){
        OrgUnit parentText = orgUnitRepository.findByIdAndParentText(orgUnit.getParentId());
        if(parentText !=null){
            orgUnitModel.setParentText(parentText.getName());
        }}
        return  orgUnitModel;
    }
}
