package com.clean.domain.entity.stc;

import com.clean.domain.entity.look.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item_distribution", schema = "stc")
@Getter
@Setter
@NoArgsConstructor
public class ItemDistribution {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @DiffInclude
    @Column(name = "code", nullable = false)
    private String code;
    @DiffInclude
    @Column(name = "document_number")
    private String documentNumber;
    @DiffInclude
    @Column(name = "date", nullable = false)
    private Date date;
    @DiffInclude
    @Column(name = "item_request_id", nullable = false)
    private long itemRequestId;
    @DiffInclude
    @Column(name = "description",length = 1000)
    private String description;
    @DiffInclude
    @Column(name = "stageId")
    private int stageId;
    @DiffInclude
    @Column(name = "status_id")
    private Integer statusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_request_id", referencedColumnName = "id", insertable = false, updatable = false)
    ItemRequest itemRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Status  status;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "itemDistribution")
    private Set<ItemDistributionDetail> itemDistributionDetails = new HashSet<>();
}
