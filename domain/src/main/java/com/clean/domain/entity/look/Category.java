package com.clean.domain.entity.look;

import com.clean.domain.entity.hr.StockKeeper;
import com.clean.domain.entity.itm.Attribute;
import com.clean.domain.entity.itm.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category",schema = "look")
@Getter@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id",nullable = false)
    private short id;
    @Column(name = "name",nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "category")
    private Set<Item> items = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "category")
    private Set<Attribute> attributes = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "category")
    private Set<StockKeeper> stockKeepers = new HashSet<>();
}
