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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entity_classification",schema = "prc")
public class EntityClassification {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;
    @DiffInclude
    @Column(name = "name",nullable = false)
    private String name;
    @DiffInclude
    @Column(name = "entity_id",nullable = false)
    private int entityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id",referencedColumnName = "id",insertable = false,updatable = false)
    private WorkFlowEntity workFlowEntity;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "entityClassification")
    private Set<WorkFlow> workFlows = new HashSet<>();
}
