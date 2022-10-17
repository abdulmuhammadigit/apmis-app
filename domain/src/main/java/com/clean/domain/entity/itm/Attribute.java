package com.clean.domain.entity.itm;

import com.clean.domain.entity.look.AttributeDataType;
import com.clean.domain.entity.look.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "attribute",schema = "itm")
@Getter
@Setter
@NoArgsConstructor
public class Attribute {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;
    @DiffInclude
    @Column(name = "category_id",nullable = false)
    private short categoryId;
    @DiffInclude
    @Column(name = "name")
    private String name;
    @DiffInclude
    @Column(name = "data_type_id",nullable = false)
    private short dataTypeId;
 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",referencedColumnName = "id",insertable = false,updatable = false)
    Category category;  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_type_id",referencedColumnName = "id",insertable = false,updatable = false)
    AttributeDataType attributeDataType;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "attribute")
    private Set<ItemAttribute> itemAttributes = new HashSet<>();
}
