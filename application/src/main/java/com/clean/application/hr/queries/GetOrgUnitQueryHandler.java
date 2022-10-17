package com.clean.application.hr.queries;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.hr.OrgUnit;
import com.clean.persistence.hr.OrgUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GetOrgUnitQueryHandler implements IRequestHandler<GetOrgUnitQuery, List<OrgUnit>> {
    private OrgUnitRepository repository;
    @Autowired
    GetOrgUnitQueryHandler(OrgUnitRepository repository){
        this.repository = repository;
    }
    @Override
    public List<OrgUnit> handle(GetOrgUnitQuery request) {
        return repository.findAll();
    }
}
