package com.clean.domain.entity.doc;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "document", schema = "doc")
@NoArgsConstructor

public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private int id;
    @Column(name = "content_type", nullable = false)
    private String contentType;
    @Column(name = "root", nullable = false)
    private  String root;
    @Column(name = "path", nullable = false)
    private String path;
    @Column(name="encryption_key")
    private String encryptionKey;
    @Column(name = "description")
    private String description;
    @Column(name = "document_number")
    private String documentNumber;
    @Column(name="document_source")
    private String documentSource;
    @Column(name="document_issue_date")
    private Timestamp documentIssueDate;
    @Column(name="upload_date" , nullable = false)
    private Timestamp uploadDate;
    @Column(name="last_download_date")
    private Timestamp lastDownloadDate; 
    @Column(name = "document_type_id")
    private int documentTypeId;
    @Column(name = "record_id")
    private long   recordId;
    @Column(name = "document_size")
    private float documentSize;
    @Column(name = "is_encrypted")
    private boolean isEncrypted;
    @Column(name = "section_id")
    private int sectionId;
    @Column(name = "upload_user_id")
    private Long uploadUserId;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_type_id", nullable = false , insertable = false,updatable = false)
    private DocumentType documentType;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "section_id", nullable = false,insertable = false,updatable = false)
    private  Section section;
}

