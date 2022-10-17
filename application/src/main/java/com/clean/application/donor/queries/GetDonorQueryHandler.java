package com.clean.application.donor.queries;

import java.util.List;
import java.util.stream.Collectors;

import com.clean.application.donor.models.DonorModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.donor.DonorRepository;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;


public class GetDonorQueryHandler implements IRequestHandler<GetDonorQuery, List<DonorModel>> {
    private DonorRepository donorRepository;
    @Autowired
    public GetDonorQueryHandler(DonorRepository donorRepository ) {
        this.donorRepository = donorRepository;
    }

    @Override
    public List<DonorModel> handle(GetDonorQuery request) {
       return donorRepository.findAll().stream().map(DonorModel::map).collect(Collectors.toList());
    }
    
}
