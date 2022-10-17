package com.clean.application.document.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DocumentSearchModel {
    private int id;
    private String documentNumber;
    private String documentSource;
    private Date lastDownloadDate;
     private Date uploadDate;
    private String sectionName;
    private String documentTypeText;
}
