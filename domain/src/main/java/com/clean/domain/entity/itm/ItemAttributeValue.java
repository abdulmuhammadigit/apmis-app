package com.clean.domain.entity.itm;

import com.clean.domain.entity.look.Unit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;

@Entity
@Table(name = "itemAttributeValue",schema = "itm")
@Getter
@Setter
@NoArgsConstructor
public class ItemAttributeValue {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private int id;
    @DiffInclude
    @Column(name = "item_detail_id" , nullable = false)
    private int itemDetailId;
    @DiffInclude
    @Column(name = "attribute_id" , nullable = false)
    private int attributeId;
    @DiffInclude
    @Column(name = "value" , nullable = false,length = 1500)
    private String value;
    @DiffInclude
    @Column(name = "unit_id")
    private Short unitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_detail_id",referencedColumnName = "id", insertable = false,updatable = false)
    ItemDetail itemDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id",referencedColumnName = "id",insertable = false,updatable = false)
    Attribute attribute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Unit unit;
}
