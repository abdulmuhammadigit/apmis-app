package com.clean.domain.entity.hr;

import com.clean.domain.entity.stc.ItemRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "employee",schema = "hr")
@NoArgsConstructor
public class Employee {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private int id;
    @DiffInclude
    @Column(name = "code" , nullable = false)
    private String code;
    @DiffInclude
    @Column(name = "name" , nullable = false)
    private String name;
    @DiffInclude
    @Column(name = "last_name")
    private String lastName;
    @DiffInclude
    @Column(name = "father_name")
    private String fatherName;
    @DiffInclude
    @Column(name = "grand_father_name")
    private String grandFatherName;
    @DiffInclude
    @Column(name = "org_unit_id" , nullable = false)
    private int orgUnitId;
    @DiffInclude
    @Column(name = "position" , nullable = false)
    private String position;
    @DiffInclude
    @Column(name = "card_id")
    private String cardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_unit_id",referencedColumnName = "id",insertable = false,updatable = false)
    private OrgUnit orgUnit;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "employee")
    private Set<StockKeeper> stockKeepers = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "employee")
    private Set<ItemRequest> itemRequests = new HashSet<>();
}
