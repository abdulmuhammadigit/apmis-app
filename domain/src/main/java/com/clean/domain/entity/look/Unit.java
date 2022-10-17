package com.clean.domain.entity.look;

import com.clean.domain.entity.stc.ItemReceiptDetail;
import com.clean.domain.entity.stc.ItemRequestDetail;
import com.clean.domain.entity.itm.Item;
import com.clean.domain.entity.itm.ItemAttributeValue;
import com.clean.domain.entity.stc.NecessityChartDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "unit",schema = "look")
@Getter
@Setter
@NoArgsConstructor
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private short id;
    @Column(name = "name" , nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "unit")
    private Set<ItemAttributeValue> itemAttributeValueSet = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "unit")
    private Set<Item> items = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "unit")
    private Set<ItemReceiptDetail> itemReceiptTypes = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "unit")
    private Set<NecessityChartDetail> necessityChartDetails = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "unit")
    private Set<ItemRequestDetail> itemRequestDetails = new HashSet<>();
}
