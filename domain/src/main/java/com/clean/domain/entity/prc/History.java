package com.clean.domain.entity.prc;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.javers.core.metamodel.annotation.DiffInclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "history",schema = "prc")
public class History {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private long id;
    @DiffInclude
    @Column(name = "record_id",nullable = false)
    private long recordId;
    @DiffInclude
    @Column(name = "work_flow_id", nullable = false)
    private int workFlowId;
    @DiffInclude
    @Column(name = "entity_id", nullable = false)
    private int entityId;
    @DiffInclude
    @Column(name = "description")
    private String description;
    @DiffInclude
    @Column(name = "date",nullable = false)
    private Timestamp date;
    @DiffInclude
    @Column(name = "user_id",nullable = false)
    private long userId;
    @DiffInclude
    @Column(name = "to_user_id")
    private int toUserId;
    @DiffInclude
    @Column(name = "from_stage_id")
    private int fromStageId;
    @DiffInclude
    @Column(name = "to_stage_id")
    private int toStageId;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "connection_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Connection connection;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_flow_id",referencedColumnName = "id",insertable = false,updatable = false)
    private WorkFlow workFlow;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id",referencedColumnName = "id",insertable = false,updatable = false)
    private WorkFlowEntity workFlowEntity;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_stage_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Stage fromStage;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_stage_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Stage toStage;
}
