package com.clean.application.audit.queries;

import com.clean.common.mediator.core.IRequest;

import lombok.Data;

@Data
public class SearchAuditLogsQuery implements IRequest<String> {
    private String entity;
    private Object entityId;
}
