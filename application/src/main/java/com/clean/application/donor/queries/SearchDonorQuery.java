package com.clean.application.donor.queries;

import com.clean.application.donor.models.DonorModel;
import com.clean.common.mediator.core.IRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDonorQuery implements IRequest<List<DonorModel>> {
    private String name ;
}
