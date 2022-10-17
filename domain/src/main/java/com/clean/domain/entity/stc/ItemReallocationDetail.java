package com.clean.domain.entity.stc;

import com.clean.domain.entity.look.Status;
import lombok.*;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;

@Entity
@Table(name = "item_reallocation_detail",schema = "stc")
@Getter
@Setter
@NoArgsConstructor
public class ItemReallocationDetail {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private long id;
    @DiffInclude
    @Column(name = "item_reallocation_id" , nullable = false)
    private long itemReallocationId;
    @DiffInclude
    @Column(name = "item_distribution_detail_id")
    private long itemDistributionDetailId;
    @DiffInclude
    @Column(name = "reallocation_quantity")
    private short reallocationQuantity;
    @DiffInclude
    @Column(name = "status_id")
    private int statusId;
    @DiffInclude
    @Column(name = "description")
    private String description;
    @DiffInclude
    @Column(name="confirmed")
    private boolean confirmed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_reallocation_id",referencedColumnName = "id",insertable = false,updatable = false)
    ItemReallocation itemReallocation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_distribution_detail_id",referencedColumnName = "id",insertable = false,updatable = false)
    ItemDistributionDetail itemDistributionDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id",referencedColumnName = "id",insertable = false,updatable = false)
    Status status;
}
