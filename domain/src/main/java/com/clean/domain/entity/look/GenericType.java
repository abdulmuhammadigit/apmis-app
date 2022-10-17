package com.clean.domain.entity.look;
import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "status",schema = "look")
@Getter
@Setter
@NoArgsConstructor
public class GenericType {
    @Id
    @Column(name = "id" , nullable = false)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "schema" , nullable = false)
    private String schema;
    @Column(name = "db_object" , nullable = false)
    private String dbObject;
    @Column(name = "category" , nullable = false)
    private String category;
    @Column(name = "description")
    private String description;
}
