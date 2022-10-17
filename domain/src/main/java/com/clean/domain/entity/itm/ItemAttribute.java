package com.clean.domain.entity.itm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;

@Entity
@Table(name = "item_attribute",schema = "itm")
@Getter
@Setter
@NoArgsConstructor
public class ItemAttribute {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private int id;
    @DiffInclude
    @Column(name = "item_id" , nullable = false)
    private int itemId;
    @DiffInclude
    @Column(name = "attribute_id" , nullable = false)
    private int attributeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id",referencedColumnName = "id", insertable = false, updatable = false)
    Attribute attribute;
}
