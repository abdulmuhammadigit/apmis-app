package com.clean.domain.entity.look;

import com.clean.domain.entity.itm.Attribute;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "attribute_data_type",schema = "look")
@Getter
@Setter
@NoArgsConstructor
public class AttributeDataType {
    @Id
    @Column(name = "id",nullable = false)
    private short id;
    @Column(name = "data_type_name",nullable = false)
    private String dataTypeName;
    @Column(name = "abbreviation",nullable = false)
    private String abbreviation;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "attributeDataType")
    private Set<Attribute> attributes = new HashSet<>();
}
