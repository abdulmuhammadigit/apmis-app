package com.clean.application.look.quries;

import java.util.List;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.look.OrgUnitType;
import com.clean.persistence.look.OrgUnitTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchOrgUnitTypeQueryHandler implements IRequestHandler<SearchOrgUnitTypeQuery,List<OrgUnitType>> {
    private OrgUnitTypeRepository orgUnitTypeRepository;
    @Autowired
    public SearchOrgUnitTypeQueryHandler(OrgUnitTypeRepository orgUnitTypeRepository) {
        this.orgUnitTypeRepository = orgUnitTypeRepository;
    }
    @Override
    public List<OrgUnitType> handle(SearchOrgUnitTypeQuery request) {
        return orgUnitTypeRepository.findAll();
    }
    
}
