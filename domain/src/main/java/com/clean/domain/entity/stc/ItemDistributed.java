package com.clean.domain.entity.stc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item_distributed",schema = "stc")
@Getter
@Setter
@NoArgsConstructor
public class ItemDistributed {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,nullable = false)
    private long id;
    @DiffInclude
    @Column(name = "item_distribution_detail_id" , nullable = false)
    private long itemDistributionDetailId;
    @DiffInclude
    @Column(name = "item_receipt_detail_id" , nullable = false)
    private long itemReceiptDetailId;
    @DiffInclude
    @Column(name = "quantity" , nullable = false)
    private short quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_receipt_detail_id" ,referencedColumnName = "id",insertable = false,updatable = false)
    ItemReceiptDetail itemReceiptDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_distribution_detail_id",referencedColumnName = "id",insertable = false,updatable = false)
    ItemDistributionDetail itemDistributionDetail;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST ,mappedBy = "itemDistributed")
    private Set<ItemDistributedSpecification> itemDistributedSpecification = new HashSet<>();
}
