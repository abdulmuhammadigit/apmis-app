package com.clean.application.document.commands;

import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.doc.Document;
import com.clean.persistence.document.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

public class DownloadFileCommandHandler implements IRequestHandler<DownloadFileCommand, ResponseEntity<Resource>> {
    private DocumentRepository repository;

    @Autowired
    public DownloadFileCommandHandler(DocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Resource> handle(DownloadFileCommand request) {
        try {
            Document document = repository.findById(request.getId()).orElseThrow(() -> new RuntimeException("فایل دریافت نگردید!"));
            String path = document.getRoot() + document.getPath();
            File file = new File(path);

            ResponseEntity<Resource> fileStream = download(file, document.getContentType(), path);
            document.setLastDownloadDate(new Timestamp(System.currentTimeMillis()));
            repository.save(document);
            return fileStream; 
        } catch (IOException ex) {
            throw new RuntimeException("دانلود ناموفق");
        }
    }

    public ResponseEntity<Resource> download(File file, String contentType, String filePath) throws IOException {

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");
        headers.add("Access-Control-Expose-Headers", "fileName,Content-Disposition");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType(contentType)).body(resource);
    }
}