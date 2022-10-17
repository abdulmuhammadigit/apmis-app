package com.clean.application.configuration.commands;

import java.sql.Date;

import com.clean.domain.entity.conf.FiscalYear;
import com.clean.persistence.configuration.FiscalYearRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class FiscalYearAutomationHandler {
    private FiscalYearRepository repository;

    @Autowired
    public FiscalYearAutomationHandler(FiscalYearRepository repository) {
        this.repository = repository;
    }
    @Scheduled(fixedDelay = 43200000)
    private void setCurrentFiscalYear(){
        FiscalYear fiscalYear =  repository.findCurrentYear(new Date(System.currentTimeMillis()));
        FiscalYear currentFiscalYear =  repository.findByCurrentFiscalYear(true);
        if(fiscalYear == null){
            if(currentFiscalYear != null){
                currentFiscalYear.setCurrentFiscalYear(false);
                repository.save(currentFiscalYear);
            }
        }else{
            if(currentFiscalYear == null){
                fiscalYear.setCurrentFiscalYear(true);
                repository.save(fiscalYear);
            }
            else if(fiscalYear.getId() != currentFiscalYear.getId()){
                currentFiscalYear.setCurrentFiscalYear(false);
                repository.save(currentFiscalYear);

                fiscalYear.setCurrentFiscalYear(true);
                repository.save(fiscalYear);
            }
        }
    }
}
