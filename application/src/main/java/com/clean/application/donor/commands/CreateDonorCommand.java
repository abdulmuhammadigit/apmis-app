package com.clean.application.donor.commands;

import com.clean.application.donor.models.DonorModel;
import com.clean.common.mediator.core.IRequest;
import com.clean.domain.entity.stc.Donor;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class CreateDonorCommand implements IRequest<Donor> {
    private int id;
    private String name;
    private String abbreviation;
}
