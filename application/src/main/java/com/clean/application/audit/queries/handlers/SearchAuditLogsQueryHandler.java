package com.clean.application.audit.queries.handlers;

import java.util.List;

import com.clean.application.audit.queries.SearchAuditLogsQuery;
import com.clean.common.mediator.core.IRequestHandler;

import org.javers.core.Javers;
import org.javers.core.json.JsonConverter;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;


public class SearchAuditLogsQueryHandler implements IRequestHandler<SearchAuditLogsQuery, String> {
    
    private final Javers javers;

    public SearchAuditLogsQueryHandler( Javers javers){
        this.javers = javers;
    }

    @Override
    public String handle(SearchAuditLogsQuery request) {
        QueryBuilder jqlQuery = QueryBuilder.byInstanceId(request.getEntityId(), request.getEntity());//.limit(500);

        List<CdoSnapshot> changes = javers.findSnapshots(jqlQuery.build());

        changes.sort((o1, o2) -> -1 * o1.getCommitMetadata().getCommitDate().compareTo(o2.getCommitMetadata().getCommitDate()));

        JsonConverter jsonConverter = javers.getJsonConverter();

        return jsonConverter.toJson(changes);
    }
}
