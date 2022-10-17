package com.clean.domain.entity.stc;

import com.clean.domain.entity.hr.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item_request",schema = "stc")
@Getter
@Setter
public class ItemRequest {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private long id;
    @DiffInclude
    @Column(name = "code" , nullable = false)
    private String code;
    @DiffInclude
    @Column(name = "employee_id" , nullable = false)
    private int employeeId;
    @DiffInclude
    @Column(name = "document_number")
    private String documentNumber;
    @DiffInclude
    @Column(name = "date" , nullable = false)
    private Date date;
    @DiffInclude
    @Column(name = "completed", nullable = false)
    private boolean completed;
    @DiffInclude
    @Column(name = "description", length = 1000)
    private String description;
    @DiffInclude
    @Column(name = "department")
    private String department;
    @DiffInclude
    @Column(name = "stageId")
    private int stageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id",referencedColumnName = "id", insertable = false, updatable = false)
    Employee employee;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "itemRequest")
    private Set<ItemRequestDetail> itemRequestDetails = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "itemRequest")
    private Set<ItemDistribution> itemDistributions = new HashSet<>();


}
