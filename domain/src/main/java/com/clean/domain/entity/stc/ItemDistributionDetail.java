package com.clean.domain.entity.stc;

import com.clean.domain.entity.itm.ItemDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item_distribution_detail",schema = "stc")
@Getter
@Setter
@NoArgsConstructor
public class ItemDistributionDetail {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @DiffInclude
    @Column(name = "item_distribution_id" , nullable = false)
    private long itemDistributionId;
    @DiffInclude
    @Column(name = "item_request_detail_id" , nullable = false)
    private long itemRequestDetailId;
    @DiffInclude
    @Column(name = "item_detail_id" , nullable = false)
    private int itemDetailId;
    @DiffInclude
    @Column(name = "quantity",nullable = false)
    private short quantity;
    @DiffInclude
    @Column(name = "reallocated_quantity")
    private short reallocatedQuantity;
    @DiffInclude
    @Column(name = "distributed" ,nullable = false)
    private boolean distributed;
    @DiffInclude
    @Column(name = "status_id",nullable = false)
    private int statusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_distribution_id" ,referencedColumnName = "id",insertable = false,updatable = false)
    ItemDistribution itemDistribution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_request_detail_id", referencedColumnName = "id",insertable = false,updatable = false)
    ItemRequestDetail itemRequestDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_detail_id",referencedColumnName = "id",insertable = false,updatable = false)
    private ItemDetail itemDetail;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "itemDistributionDetail")
    private Set<ItemDistributed> itemDistributeds = new HashSet<>();
}
