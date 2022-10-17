package com.clean.domain.entity.prc;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "stage",schema = "prc")
public class Stage {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @DiffInclude
    @Column(name = "name",nullable = false)
    private String name;
    @DiffInclude
    @Column(name = "work_flow_id",nullable = false)
    private int workFlowId;
    @DiffInclude
    @Column(name = "stage_type_id" , nullable = false)
    private int stageTypeId;
    @DiffInclude
    @Column(name = "is_active")
    private boolean isActive;
    @DiffInclude
    @Column(name = "is_editable")
    private boolean isEditable;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_flow_id",referencedColumnName = "id",insertable = false,updatable = false)
    private WorkFlow workFlow;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_type_id",referencedColumnName = "id",insertable = false,updatable = false)
    private StageType stageType;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "stage")
    private Set<Connection> connections = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "toStage")
    private Set<Connection> toConnections = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "fromStage")
    private Set<History> fromHistories = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "toStage")
    private Set<History> toHistories = new HashSet<>();
}
