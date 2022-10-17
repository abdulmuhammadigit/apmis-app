package com.clean.application.configuration.commands;

import java.util.Date;

import com.clean.application.configuration.models.FiscalYearModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.conf.FiscalYear;
import com.clean.persistence.configuration.FiscalYearRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CreateFiscalYearCommandHandler implements IRequestHandler<CreateFiscalYearCommand,FiscalYearModel> {
    private FiscalYearRepository repository;
    @Autowired
    public CreateFiscalYearCommandHandler(FiscalYearRepository repository) {
        this.repository = repository;
    }
    @Override
    public FiscalYearModel handle(CreateFiscalYearCommand request) {

        Date startDate=request.getStartDate();
        Date endDate = request.getEndDate();
        if(startDate.equals(endDate) || startDate.after(endDate)){
            throw new RuntimeException("تاریخ ختم سال مالی نمیتواند مساوی و کوچکتر از شروع سال مالی باشد!");
        }

        if(repository.existsByStartDate(request.getStartDate(),request.getId()) != null && request.getId() != 0){
            throw new RuntimeException("شروع سال مالی تداخل با سال مالی قبلی دارد!");
        }

        FiscalYear fiscalYear = repository.findById(request.getId()).orElse(null);
        if(fiscalYear == null){
            fiscalYear = new FiscalYear();
        }



        fiscalYear.setStartDate(request.getStartDate());
        fiscalYear.setEndDate(request.getEndDate());
        fiscalYear.setShamsiYear(request.getShamsiYear());
        fiscalYear.setCurrentFiscalYear(false);
        repository.save(fiscalYear);
        return FiscalYearModel.map(fiscalYear);
    }
    
}
