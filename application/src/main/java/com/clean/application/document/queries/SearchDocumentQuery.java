package com.clean.application.document.queries;

import com.clean.application.document.models.DocumentSearchModel;
import com.clean.common.mediator.core.IRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchDocumentQuery implements IRequest<List<DocumentSearchModel>> {
    private long recordId;
    private int sectionId;
}
