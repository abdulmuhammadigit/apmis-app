package com.clean.domain.entity.sec;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStagePK implements Serializable {
    private Long userId;
    private Integer stageId;
}
