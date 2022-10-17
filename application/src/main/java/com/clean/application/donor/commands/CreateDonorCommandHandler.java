package com.clean.application.donor.commands;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.Donor;
import com.clean.persistence.donor.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateDonorCommandHandler implements IRequestHandler<CreateDonorCommand, Donor> {
    private DonorRepository donorRepository;
    @Autowired
    CreateDonorCommandHandler(DonorRepository donorRepository){
        this.donorRepository = donorRepository;
    }
    @Override
    public Donor handle(CreateDonorCommand request) {
        Donor donor = donorRepository.findById(request.getId()).orElse(null);
        if(donor ==null){
              donor = new Donor();
        }
        donor.setName(request.getName());
        donor.setAbbreviation(request.getAbbreviation());

        return  donorRepository.save(donor);
    }
}
