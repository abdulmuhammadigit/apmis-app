package com.clean.application.document.commands;

import com.clean.application.document.models.DocumentSearchModel;
import com.clean.common.mediator.core.IRequest;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class SaveDocumentCommand implements IRequest<List<DocumentSearchModel>> {
    private int id;
    private int sectionId;
    private int documentTypeId;
    private String description;
    private String documentNumber;
    private String documentSource;
    private long recordId;
    MultipartFile file;
}
