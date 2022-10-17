package com.clean.domain.entity.look;

import com.clean.domain.entity.hr.StockKeeper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stock_keeper_type",schema = "look")
@Getter
@Setter
@NoArgsConstructor
public class StockKeeperType {
    @Id
    @Column(name = "id" , nullable = false)
    private short id;
    @Column(name = "name" , nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "stockKeeperType")
    private Set<StockKeeper> stockKeepers = new HashSet<>();
}
