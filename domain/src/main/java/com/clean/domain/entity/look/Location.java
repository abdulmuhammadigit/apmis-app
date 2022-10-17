package com.clean.domain.entity.look;

import com.clean.domain.entity.hr.StockKeeper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Cleanup;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "location",schema = "look")
@Getter
@Setter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private int id;
    @Column(name = "name" , nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "location")
    private Set<StockKeeper> stockKeepers = new HashSet<>();
}
