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
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "work_flow",schema = "prc")
public class WorkFlow {
    @DiffInclude
    @Id
    @Column(name = "id" ,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @DiffInclude
    @Column(name = "name" , nullable = false)
    private String name;
    @DiffInclude
    @Column(name = "parent_id")
    private Integer parentId;
    @DiffInclude
    @Column(name = "entity_id" , nullable = false)
    private int entityId;
    @DiffInclude
    @Column(name = "classification_id")
    private Integer classificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id",referencedColumnName = "id",insertable = false,updatable = false)
    private WorkFlow workFlow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id",referencedColumnName = "id",insertable = false,updatable = false)
    private WorkFlowEntity workFlowEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classification_id",referencedColumnName = "id",insertable = false,updatable = false)
    private EntityClassification entityClassification;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "workFlow")
    private Set<History> histories = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "workFlow")
    private Set<Stage> stages= new HashSet<>();
}
