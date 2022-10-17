package com.clean.domain.entity.stc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@Entity
@Table(name = "donor",schema = "stc")
@NoArgsConstructor
public class Donor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private int id;
    @Column(name = "name" , nullable = false)
    private String name;
    @Column(name = "abbreviation" , nullable = false)
    private String abbreviation;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "donor")
    private Set<ItemReceipt> itemReceipts = new HashSet<>();
}
