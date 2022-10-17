package com.clean.domain.entity.itm;

import com.clean.domain.entity.stc.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item_detail",schema = "itm")
@Getter@Setter
@NoArgsConstructor
public class ItemDetail {
    @DiffInclude
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private int id;
    @DiffInclude
    @Column(name = "item_id" , nullable = false)
    private int itemId;
    @DiffInclude
    @Column(name = "detail",nullable = false)
    private String detail;
    @DiffInclude
    @Column(name ="code") 
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id",referencedColumnName = "id",insertable = false,updatable = false)
    Item item;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "itemDetail")
    private Set<ItemReceiptDetail> itemReceiptDetails = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "itemDetail")
    private Set<ItemRequestDetail> itemRequestDetails = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "itemDetail")
    private Set<NecessityChartDetail> necessityChartDetails = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "itemDetail")
    private Set<ItemDistributionDetail> itemDistributionDetails = new HashSet<>();
}
