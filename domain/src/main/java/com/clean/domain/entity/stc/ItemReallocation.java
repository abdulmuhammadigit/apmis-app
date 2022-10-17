package com.clean.domain.entity.stc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item_reallocation",schema = "stc")
@Getter
@Setter
@NoArgsConstructor
public class ItemReallocation {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private long id;
    @DiffInclude
    @Column(name = "code" , nullable = false)
    private String code;
    @DiffInclude
    @Column(name = "document_number")
    private String documentNumber;
    @DiffInclude
    @Column(name = "date" , nullable = false)
    private Date date;
    @DiffInclude
    @Column(name = "description")
    private String description;
    @DiffInclude
    @Column(name = "stage_Id")
    private int stageId;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "itemReallocation")
    private Set<ItemReallocationDetail> itemReallocationDetails = new HashSet<>();
}
