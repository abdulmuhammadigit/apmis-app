package com.clean.application.processtracking.models;

import com.clean.domain.entity.prc.Connection;
import com.clean.domain.entity.prc.History;
import com.clean.domain.entity.prc.Stage;
import lombok.Data;

@Data
public class InitialHistoryCommandResult {
    private Connection connection;
    private History history;
    private Stage initial;
    private Stage toStage;
}
