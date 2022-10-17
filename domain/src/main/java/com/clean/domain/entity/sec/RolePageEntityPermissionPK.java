package com.clean.domain.entity.sec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePageEntityPermissionPK implements Serializable {
    private Integer rolePageId;
    private Integer entityId;
    private Integer entityPermissionId;
}
