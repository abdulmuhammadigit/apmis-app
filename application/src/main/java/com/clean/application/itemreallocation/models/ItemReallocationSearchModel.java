package com.clean.application.itemreallocation.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ItemReallocationSearchModel {
    private long id;
    private String code;
    private String documentNumber;
    private Date date;
    private String description;
}
