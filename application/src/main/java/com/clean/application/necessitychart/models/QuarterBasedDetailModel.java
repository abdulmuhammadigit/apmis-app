package com.clean.application.necessitychart.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuarterBasedDetailModel {
    String quarterText;
    short quantity;
    short quarterId;
}
