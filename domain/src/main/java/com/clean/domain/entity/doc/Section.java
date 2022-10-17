package com.clean.domain.entity.doc;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "section", schema = "doc")
@NoArgsConstructor

public class Section {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name ="id" , nullable = false)
    private int id;
    @Column(name = "section_name")
    private String sectionName;
    @Column(name = "object_name")
    private String objectName;
    @Column(name="object_schema")
    private String objectSchema;
    @Column(name = "description")
    private String description;
    
    @JsonIgnore
    @OneToMany(mappedBy = "section" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Document> documents = new HashSet<>();


}
