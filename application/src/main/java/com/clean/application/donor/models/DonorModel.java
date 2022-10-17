package com.clean.application.donor.models;

import com.clean.domain.entity.stc.Donor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DonorModel {
    private int id;
    private String name;
    private String abbreviation;

    public  static DonorModel map(Donor donor){
        DonorModel model = new DonorModel();
        model.setId(donor.getId());
        model.setName(donor.getName());
        model.setAbbreviation(donor.getAbbreviation());
        return  model;
    }
}
