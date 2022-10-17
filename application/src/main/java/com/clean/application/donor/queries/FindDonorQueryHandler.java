package com.clean.application.donor.queries;

import com.clean.application.donor.models.DonorModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.stc.Donor;
import com.clean.persistence.donor.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class FindDonorQueryHandler implements IRequestHandler<FindDonorQuery, DonorModel> {
    private DonorRepository donorRepository;

    @Autowired
    FindDonorQueryHandler(DonorRepository donorRepository){
        this.donorRepository = donorRepository;
     }
    @Override
    public  DonorModel handle(FindDonorQuery request){
        Donor donor = donorRepository.findById(request.getId()).orElseThrow(()-> new RuntimeException("تمویل کننده دریافت نگردید!"));
        DonorModel donorModel = DonorModel.map(donor);
        return donorModel;
    }


}