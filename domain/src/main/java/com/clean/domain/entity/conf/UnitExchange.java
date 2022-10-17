package com.clean.domain.entity.conf;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;

import com.clean.domain.entity.itm.Item;

@Entity
@Table(name = "unit_exchange",schema = "stc")
@Getter
@Setter
@NoArgsConstructor
public class UnitExchange {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private int id;
    @DiffInclude
    @Column(name = "unit_id",nullable = false)
    private int unitId;
    @DiffInclude
    @Column(name = "quantity",nullable = false)
    private short quantity;
    @DiffInclude
    @Column(name = "to_unit_id",nullable = false)
    private int toUnitId;
    @DiffInclude
    @Column(name = "item_id")
    private Integer itemId; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id",referencedColumnName = "id",insertable = false,updatable = false)
    Item item;
}
