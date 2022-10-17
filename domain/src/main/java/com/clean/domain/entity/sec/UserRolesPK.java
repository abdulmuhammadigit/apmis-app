package com.clean.domain.entity.sec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRolesPK implements Serializable {
    private Long userId;
    private Integer roleId;
}
