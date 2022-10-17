package com.clean.application.document.commands;

import java.io.File;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.doc.Document;
import com.clean.persistence.document.DocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class DeleteDocumentCommandHandler implements IRequestHandler<DeleteDocumentCommand, Boolean> {

    private DocumentRepository repository;

    @Autowired
    DeleteDocumentCommandHandler(DocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean handle(DeleteDocumentCommand request) {
        Document document = repository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("فایل دریافت نگردید!"));
        String path = document.getRoot() + document.getPath();
        File file = new File(path);
        file.delete();

        repository.delete(document);
        return true;
    }

}
