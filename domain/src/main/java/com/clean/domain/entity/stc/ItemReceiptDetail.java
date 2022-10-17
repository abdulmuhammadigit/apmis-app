package com.clean.domain.entity.stc;

import com.clean.domain.entity.itm.ItemDetail;
import com.clean.domain.entity.look.Unit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item_receipt_detail",schema = "stc")
@Getter@Setter
@NoArgsConstructor
public class ItemReceiptDetail {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private long id;
    @DiffInclude
    @Column(name = "item_receipt_id" , nullable = false)
    private long itemReceiptId;
    @DiffInclude
    @Column(name = "item_detail_id" , nullable = false)
    private int itemDetailId;
    @DiffInclude
    @Column(name = "unit_id" , nullable = false)
    private short unitId;
    @DiffInclude
    @Column(name = "quantity" , nullable = false)
    private short quantity;
    @DiffInclude
    @Column(name = "base_quantity" , nullable = false)
    private short baseQuantity;
    @DiffInclude
    @Column(name = "price" , nullable = false)
    private double price;
    @DiffInclude
    @Column(name = "description")
    private String description;
    @DiffInclude
    @Column(name = "remain", nullable = false)
    private short remain;
    @DiffInclude
    @Column(name = "finished" , nullable = false)
    private boolean finished;
    @DiffInclude
    @Column(name = "local_product",nullable = false)
    private boolean localProduct;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_receipt_id",referencedColumnName = "id",insertable = false,updatable = false)
    private ItemReceipt itemReceipt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_detail_id",referencedColumnName = "id",insertable = false,updatable = false)
    private ItemDetail itemDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Unit unit;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "itemReceiptDetail")
    private Set<ItemDistributed> itemDistributeds = new HashSet<>();
}
