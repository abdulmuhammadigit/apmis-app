package com.clean.application.document.commands;

import com.clean.common.mediator.core.IRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import org.springframework.core.io.Resource;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class DownloadFileCommand implements IRequest<ResponseEntity<Resource>> {
    private int id;
}
