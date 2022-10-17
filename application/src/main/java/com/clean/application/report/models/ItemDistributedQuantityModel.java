package com.clean.application.report.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class ItemDistributedQuantityModel {

    private long id;
    private String orgUnitName;
    private int count;

}


