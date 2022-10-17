package com.clean.domain.entity.sec;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@javax.persistence.Entity
@Table(name = "user_stage", schema = "sec")
@IdClass(UserStagePK.class)
public class UserStage implements Serializable {
    @DiffInclude
    @Id
    @Basic
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @DiffInclude
    @Id
    @Basic
    @Column(name = "stage_id", nullable = false)
    private Integer stageId;
}
