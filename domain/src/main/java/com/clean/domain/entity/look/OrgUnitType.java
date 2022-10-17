package com.clean.domain.entity.look;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "org_unit_type" , schema = "look")
@Getter
@Setter
@NoArgsConstructor
public class OrgUnitType {
    @Id
    @Column(name = "id" , nullable = false)
    private int id;
    @Column(name = "name" , nullable = false)
    private String name;
}
