package com.clean.application.util.commands;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.common.utility.CodeGenerator;
import com.clean.domain.entity.conf.CodeSequence;
import com.clean.domain.entity.conf.FiscalYear;
import com.clean.persistence.configuration.CodeSequenceRepository;
import com.clean.persistence.configuration.FiscalYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCodeSequenceCommandHandler implements IRequestHandler<CreateCodeSequenceCommand, String> {
    private CodeSequenceRepository codeSequenceRepository;
    private FiscalYearRepository fiscalYearRepository;
    @Autowired
    CreateCodeSequenceCommandHandler(CodeSequenceRepository codeSequenceRepository, FiscalYearRepository fiscalYearRepository) {
        this.codeSequenceRepository = codeSequenceRepository;
        this.fiscalYearRepository = fiscalYearRepository;
    }
    @Override
    public String handle(CreateCodeSequenceCommand request) {
        FiscalYear fiscalYear = fiscalYearRepository.findByCurrentFiscalYear(true);
        CodeSequence codeSequence = codeSequenceRepository.getByFormOrYear(request.getFormPrefix(),fiscalYear.getId());

        if(codeSequence == null){
            codeSequence = new CodeSequence();
            codeSequence.setSequence(0);
            codeSequence.setFiscalYearId(fiscalYear.getId());
            codeSequence.setForm(request.getFormPrefix());
        }
        int sequence = codeSequence.getSequence()+1;

        codeSequence.setSequence(sequence);
        codeSequenceRepository.save(codeSequence);

        //REMAIN: get organization code from data base according to user organization
        String organizationCode= "NSIA";
        return CodeGenerator.generate(request.getFormPrefix(),organizationCode, fiscalYear.getShamsiYear(),sequence);
    }
}
