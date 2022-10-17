package com.clean.application.hr.commands;

import com.clean.application.hr.models.OrgUnitModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.hr.OrgUnit;
import com.clean.persistence.hr.OrgUnitRepository;
import com.clean.persistence.look.OrgUnitTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOrgUnitCommandHandler implements IRequestHandler<CreateOrgUnitCommand, OrgUnitModel> {
    private OrgUnitRepository orgUnitRepository;
    private OrgUnitTypeRepository orgUnitTypeRepository;
    @Autowired
    CreateOrgUnitCommandHandler(
            OrgUnitRepository orgUnitRepository,
            OrgUnitTypeRepository orgUnitTypeRepository
    )
    {
        this.orgUnitRepository = orgUnitRepository;
        this.orgUnitTypeRepository = orgUnitTypeRepository;
    }
    @Override
    public OrgUnitModel handle(CreateOrgUnitCommand request) {
        OrgUnit orgUnit = orgUnitRepository.findById(request.getId()).orElse(null);
        if (orgUnit == null) {
            String Sorter ="1";
             orgUnit = new OrgUnit();
            // String parentSorter =Integer.toString(request.getParentId());
            // long count = orgUnitRepository.count();
            // Sorter = parentSorter + '.' + (count + 1);
             orgUnit.setSorter(Sorter);
        }
        orgUnit.setOrgUnitTypeId(request.getOrgUnitTypeId());
        orgUnit.setName(request.getName());
        orgUnit.setParentId(request.getParentId());
        orgUnit = orgUnitRepository.save(orgUnit);
        OrgUnitModel orgUnitModel = OrgUnitModel.map(orgUnit);
        String OrgUnitType= orgUnitTypeRepository.findById(orgUnit.getOrgUnitTypeId()).get().getName();
        orgUnitModel.setOrgUnitTypeName(OrgUnitType);
        
        if(orgUnit.getParentId() != null){
            OrgUnit parentText = orgUnitRepository.findByParentId(orgUnit.getParentId());

            orgUnitModel.setParentText(parentText.getName());
        }
        return orgUnitModel;
    }
}
