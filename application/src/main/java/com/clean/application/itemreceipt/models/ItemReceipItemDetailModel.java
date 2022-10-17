package com.clean.application.itemreceipt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemReceipItemDetailModel {
    private long id;
    private String itemDetailText;
}
