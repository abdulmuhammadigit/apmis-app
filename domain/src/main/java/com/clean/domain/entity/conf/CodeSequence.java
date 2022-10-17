package com.clean.domain.entity.conf;

import lombok.Getter;
import lombok.Setter;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;

@Entity
@Table(name="code_sequence",schema = "conf")
@Getter
@Setter
public class CodeSequence {

    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private long id;

    @DiffInclude
    @Column(name = "form",nullable = false)
    private String form;

    @DiffInclude
    @Column(name = "fiscal_year_Id" ,nullable = false)
    private int fiscalYearId;

    @DiffInclude
    @Column(name = "sequence",nullable = false)
    private int sequence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fiscal_year_id",referencedColumnName = "id",insertable = false,updatable = false)
    FiscalYear fiscalYear;



}
