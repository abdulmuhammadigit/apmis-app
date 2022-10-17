package com.clean.domain.entity.sec;

import lombok.*;
import org.javers.core.metamodel.annotation.DiffInclude;

import java.io.Serializable;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@javax.persistence.Entity
@Table(name = "user_roles", schema = "sec")
@IdClass(UserRolesPK.class)
public class UserRoles implements Serializable {
    @DiffInclude
    @Id
    @Basic
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @DiffInclude
    @Id
    @Basic
    @Column(name = "role_id", nullable = false)
    private Integer roleId;
}
