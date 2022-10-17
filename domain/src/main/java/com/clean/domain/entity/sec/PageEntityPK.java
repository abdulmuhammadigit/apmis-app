package com.clean.domain.entity.sec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageEntityPK implements Serializable {
    private Integer pageId;
    private Integer entityId;
}
