package com.clean.domain.entity.prc;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "work_flow_entity",schema = "prc")
public class WorkFlowEntity {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @DiffInclude
    @Column(name = "code",nullable = false)
    private String code;
    @DiffInclude
    @Column(name = "description",nullable = false)
    private String description;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "workFlowEntity")
    private Set<WorkFlow> workFlows = new HashSet<>();
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "workFlowEntity")
    private Set<BusinessRule> businessRules = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "workFlowEntity")
    private Set<EntityClassification> entityClassifications = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "workFlowEntity")
    private Set<History> histories = new HashSet<>();
}
