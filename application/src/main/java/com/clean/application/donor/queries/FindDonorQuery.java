package com.clean.application.donor.queries;

import com.clean.application.donor.models.DonorModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;
@Data
public class FindDonorQuery implements IRequest<DonorModel>{
    private  int id;
}
