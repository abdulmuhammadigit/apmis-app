package com.clean.domain.entity.stc;

import com.clean.domain.entity.look.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "item_specification",schema = "stc")
@Getter
@Setter
@NoArgsConstructor
public class ItemSpecification {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private long id;
    @DiffInclude
    @Column(name = "item_receipt_detail_id" , nullable = false)
    private long itemReceiptDetailId;
    @DiffInclude
    @Column(name = "serial_number")
    private String serialNumber;
    @DiffInclude
    @Column(name = "tag_number")
    private String tagNumber;
    @DiffInclude
    @Column(name = "expiration_date")
    private Date expirationDate;
    @DiffInclude
    @Column(name = "location")
    private String location;
    @DiffInclude
    @Column(name = "price", nullable = false)
    private double price;
    @DiffInclude
    @Column(name = "status_id")
    private Integer statusId;

    @ManyToOne
    @JoinColumn(name = "item_receipt_detail_id",insertable = false,updatable = false,referencedColumnName = "id")
    ItemReceiptDetail itemReceiptDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Status status;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "itemSpecification")
    private ItemDistributedSpecification itemDistributedSpecification;
}
