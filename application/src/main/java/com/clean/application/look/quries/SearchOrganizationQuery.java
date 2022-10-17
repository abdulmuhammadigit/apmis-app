package com.clean.application.look.quries;

import java.util.List;

import com.clean.common.mediator.core.IRequest;
import com.clean.domain.entity.look.Organization;

import lombok.Data;

@Data
public class SearchOrganizationQuery implements IRequest<List<Organization>> {
    
}
