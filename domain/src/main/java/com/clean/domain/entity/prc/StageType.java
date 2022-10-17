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

@Entity
@Table(name = "stage_type",schema = "prc")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StageType {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @DiffInclude
    @Column(name = "name",nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "stageType")
    private Set<Stage> stages= new HashSet<>();
}
