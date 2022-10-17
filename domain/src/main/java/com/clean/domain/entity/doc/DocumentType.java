package com.clean.domain.entity.doc;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "document_type", schema = "doc")
@NoArgsConstructor
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name="name", nullable = false)
    private String name;
    
    @JsonIgnore
    @OneToMany(mappedBy = "documentType", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Document> documents = new HashSet<>();
}
