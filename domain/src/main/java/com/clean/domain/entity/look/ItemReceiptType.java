package com.clean.domain.entity.look;

import com.clean.domain.entity.stc.ItemReceipt;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item_receipt_type", schema = "look")
@Setter@Getter
@NoArgsConstructor
public class ItemReceiptType {
    @Id
    @Column(name = "id" , nullable = false)
    private short id;
    @Column(name = "name" , nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "itemReceiptType")
    private Set<ItemReceipt> itemReceipts = new HashSet<>();
}
