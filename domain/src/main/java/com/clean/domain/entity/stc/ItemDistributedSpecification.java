package com.clean.domain.entity.stc;

import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.clean.domain.entity.look.Status;
import org.javers.core.metamodel.annotation.DiffInclude;

@Entity
@Table(name = "item_distributed_specification", schema = "stc")
@NoArgsConstructor
@Getter
@Setter
public class ItemDistributedSpecification {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @DiffInclude
    @Column(name = "item_distributed_id", nullable = false)
    private long itemDistributedId;
    @DiffInclude
    @Column(name = "item_specification_id")
    private long itemSpecificationId;
    @DiffInclude
    @Column(name = "reallocated")
    private boolean reallocated;
    @DiffInclude
    @Column(name = "status_id")
    private Integer statusId;
    @DiffInclude
    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_distributed_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ItemDistributed itemDistributed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Status  status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_specification_id",referencedColumnName = "id",insertable = false,updatable = false)
    ItemSpecification itemSpecification;
}
