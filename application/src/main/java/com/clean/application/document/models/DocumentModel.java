package com.clean.application.document.models;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class DocumentModel {
    private int id;
    private String contentType;
    private String root;
    private String path;
    private String encriptionKey;
    private String description;
    private String documentNumber;
    private String documentSource;
    private Timestamp documentIssueDate;
    private Timestamp uploadDate;
    private Timestamp lastDownloadDate;
    private String documentIssueDateShamsi;
    private String uploadDateShamsi;
    private  String lastDownloadDateShamsi;
    private int documentTypeId;
    private String documentTypeText;
    private long recordId;
    private float documentSize;
    private boolean status;
    private int sectionId;
}
