package com.clean.application.document.commands;

import com.clean.application.document.models.DocumentModel;
import com.clean.application.document.models.DocumentSearchModel;
import com.clean.application.document.queries.SearchDocumentQuery;
import com.clean.application.services.UserService;
import com.clean.common.mediator.core.IMediator;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.doc.Document;
import com.clean.persistence.document.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class SaveDocumentCommandHandler implements IRequestHandler<SaveDocumentCommand, List<DocumentSearchModel>> {
    private DocumentRepository repository;
    private UserService userService;
    private IMediator mediator;
    @Value("${fileUploadPath}")
    String uploadPath;

    @Autowired
    SaveDocumentCommandHandler(DocumentRepository repository,UserService userService,IMediator mediator) {
        this.repository = repository;
        this.userService = userService;
        this.mediator = mediator;
    }

    @Override
    public List<DocumentSearchModel> handle(SaveDocumentCommand request) {
        String path="";
        try {   
            //generate file new name
            String fileName = this.generateFileName(request.getFile());
            // find file store section
            File uploadFolder = new File(uploadPath);

            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }

            path = uploadFolder.getAbsolutePath() + "/" + fileName;
            File file = new File(path);
            // create the file
            if (!file.exists()) {

                file.createNewFile();
            } else {
                throw new RuntimeException("فایل از قبل موجود میباشد!");
            }

            FileOutputStream stream = new FileOutputStream(file);
            stream.write(request.getFile().getBytes());
            stream.close();

            // database file upload
            Document document = repository.findById(request.getId()).orElse(null);

            if (document == null) {
                document = new Document();
            }
            document.setSectionId(request.getSectionId());
            document.setDocumentTypeId(request.getDocumentTypeId());
            document.setContentType(request.getFile().getContentType());
            document.setDocumentSize(request.getFile().getSize());
            document.setDescription(request.getDescription());
            document.setDocumentNumber(request.getDocumentNumber());
            document.setDocumentSource(request.getDocumentSource());
            document.setRecordId(request.getRecordId());
            document.setRoot(uploadPath);
            document.setPath(fileName);
            document.setUploadDate(new Timestamp(System.currentTimeMillis()));
            document.setUploadUserId(userService.getUserId());
            document = repository.save(document);
            return mediator.send(SearchDocumentQuery.builder().sectionId(document.getSectionId()).recordId(document.getRecordId()).build());
        } catch (Exception e) {
            if(!path.isEmpty()){
                File file = new File(path);
                file.delete();
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    public String generateFileName(MultipartFile file) {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String generatedName = UUID.randomUUID().toString();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        return generatedName + fileExtension;
    }

}