package com.clean.application.document.queries;

import com.clean.application.document.models.DocumentSearchModel;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.persistence.util.queryengine.ConditionType;
import com.clean.persistence.util.queryengine.QueryEngine;
import com.clean.persistence.util.queryengine.WhereConditionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SearchDocumentQueryHandler implements IRequestHandler<SearchDocumentQuery, List<DocumentSearchModel>> {
    private EntityManager manager;

    @Autowired
    public SearchDocumentQueryHandler(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<DocumentSearchModel> handle(SearchDocumentQuery request) {
        QueryEngine engine = new QueryEngine(this.manager);
        engine.from("Document", "D").innerJoin("Section", "S", "D.sectionId", "S.id")
                .innerJoin("DocumentType", "T", "D.documentTypeId", "T.id")
                .addSelect("D.id")
                .addSelect("D.documentNumber")
                .addSelect("D.documentSource")
                .addSelect("D.lastDownloadDate")
                .addSelect("D.uploadDate")
                .addSelect("S.sectionName")
                .addSelect("T.name");
        engine.where(WhereConditionType.AND, "D.recordId", ConditionType.EQUAL, request.getRecordId());
        engine.where(WhereConditionType.AND, "D.sectionId", ConditionType.EQUAL, request.getSectionId());

        List<DocumentSearchModel> models = engine.execute(DocumentSearchModel.class);
        return models;
    }
}
