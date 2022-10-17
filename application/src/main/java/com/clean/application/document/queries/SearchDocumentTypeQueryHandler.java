package com.clean.application.document.queries;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.doc.DocumentType;
import com.clean.persistence.document.DocumentTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SearchDocumentTypeQueryHandler  implements IRequestHandler<SearchDocumentTypeQuery, List<DocumentType>> {
    private DocumentTypeRepository repository;
    @Autowired
    public SearchDocumentTypeQueryHandler(DocumentTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DocumentType> handle(SearchDocumentTypeQuery request) {
        return repository.findAll();
    }
}
