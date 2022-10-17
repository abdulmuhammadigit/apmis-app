package com.clean.application.look.quries;

import java.util.List;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.look.Organization;
import com.clean.persistence.look.OrganizationRepository;

public class SearchOrganizationQueryHandler implements IRequestHandler<SearchOrganizationQuery,List<Organization>> {
    private OrganizationRepository organizationRepository;
    public SearchOrganizationQueryHandler(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }
    @Override
    public List<Organization> handle(SearchOrganizationQuery request) {
        return organizationRepository.findAll();
    }
    
}
