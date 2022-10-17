package com.clean.domain.entity.look;

import lombok.*;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.clean.domain.entity.stc.ItemDistributedSpecification;
import com.clean.domain.entity.stc.ItemDistribution;
import com.clean.domain.entity.stc.ItemReceipt;
import com.clean.domain.entity.stc.ItemRequestDetail;
import com.clean.domain.entity.stc.ItemSpecification;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "status",schema = "look")
@Getter
@Setter
@NoArgsConstructor
public class Status {
    @Id
    @Column(name = "id" , nullable = false)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "schema" , nullable = false)
    private String schema;
    @Column(name = "db_object" , nullable = false)
    private String dbObject;
    @Column(name = "category" , nullable = false)
    private String category;
    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "status")
    private Set<ItemRequestDetail> itemRequestDetail = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "status")
    private Set<ItemDistribution> itemDistribution = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "status")
    private Set<ItemDistributedSpecification> itemDistributedSpecification = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "status")
    private Set<ItemSpecification> itemSpecifications = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "status")
    private Set<ItemReceipt> itemReceipts = new HashSet<>();
}
