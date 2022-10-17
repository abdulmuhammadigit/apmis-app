package com.clean.application.configuration.models;

import java.sql.Date;

import com.clean.domain.entity.conf.FiscalYear;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiscalYearModel {
    private int id;
    private int shamsiYear;
    private Date startDate;
    private Date endDate;
    private boolean currentFiscalYear;

    public static FiscalYearModel map(FiscalYear entity){
        FiscalYearModel model = new FiscalYearModel();
        model.setCurrentFiscalYear(entity.isCurrentFiscalYear());
        model.setStartDate(entity.getStartDate());
        model.setEndDate(entity.getEndDate());
        model.setId(entity.getId());
        model.setShamsiYear(entity.getShamsiYear());
        return model;
    }
}
