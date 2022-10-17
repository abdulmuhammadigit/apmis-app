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

@Entity
@Table(name = "connection",schema = "prc")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Connection {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;
    @DiffInclude
    @Column(name = "stage_id",nullable = false)
    private int stageId;
    @DiffInclude
    @Column(name = "to_stage_id",nullable = false)
    private int toStageId;
    @DiffInclude
    @Column(name = "business_rule_id")
    private int businessRuleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Stage stage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_stage_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Stage toStage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_rule_id",referencedColumnName = "id",insertable = false,updatable = false)
    private BusinessRule businessRule;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "connection")
    private Set<History> histories = new HashSet<>();
}
