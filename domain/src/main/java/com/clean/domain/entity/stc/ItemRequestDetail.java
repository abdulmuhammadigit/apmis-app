package com.clean.domain.entity.stc;

import com.clean.domain.entity.itm.ItemDetail;
import com.clean.domain.entity.look.Status;
import com.clean.domain.entity.look.Unit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="item_request_detail",schema = "stc")
@Getter
@Setter
@NoArgsConstructor
public class ItemRequestDetail {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private long id;
    @DiffInclude
    @Column(name = "item_request_id" , nullable = false)
    private long itemRequestId;
    @DiffInclude
    @Column(name = "item_detail_id" , nullable = false)
    private int itemDetailId;
    @DiffInclude
    @Column(name = "quantity" , nullable = false)
    private short quantity;
    @DiffInclude
    @Column(name = "base_quantity" , nullable = false)
    private short baseQuantity;
    @DiffInclude
    @Column(name = "unit_id",nullable = false)
    private short unitId;
    @DiffInclude
    @Column(name = "remain", nullable = false)
    private short remain;
    @DiffInclude
    @Column(name = "completed" , nullable = false)
    private boolean completed;
    @DiffInclude
    @Column(name = "description")
    private String description;
    @DiffInclude
    @Column(name = "status_id")
    private Integer statusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_request_id",referencedColumnName = "id",insertable = false,updatable = false)
    private ItemRequest itemRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Unit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_detail_id",referencedColumnName = "id",insertable = false,updatable = false)
    private ItemDetail itemDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Status status;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "itemRequestDetail")
    private Set<ItemDistributionDetail> itemDistributionDetail = new HashSet<>();


}
