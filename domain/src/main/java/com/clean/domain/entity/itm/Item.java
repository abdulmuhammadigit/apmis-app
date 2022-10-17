package com.clean.domain.entity.itm;

import com.clean.domain.entity.conf.UnitExchange;
import com.clean.domain.entity.look.Category;
import com.clean.domain.entity.look.Unit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item",schema = "itm")
@Getter
@Setter
@NoArgsConstructor
public class Item {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private int id;
    @DiffInclude
    @Column(name = "name" , nullable = false)
    private String name;
    @DiffInclude
    @Column(name = "category_id" , nullable = false)
    private short categoryId;
    @DiffInclude
    @Column(name = "consumable" , nullable = false)
    private Boolean consumable;
    @DiffInclude
    @Column(name = "unit_id",nullable = false)
    private short unitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Unit unit;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "item")
    private Set<ItemAttribute> itemAttributes = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "item")
    private Set<ItemDetail> itemDetails = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "item")
    private Set<UnitExchange> unitExchanges = new HashSet<>();
}
