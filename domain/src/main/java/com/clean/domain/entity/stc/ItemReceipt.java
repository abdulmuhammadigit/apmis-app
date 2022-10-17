package com.clean.domain.entity.stc;

import com.clean.domain.entity.hr.StockKeeper;
import com.clean.domain.entity.look.ItemReceiptType;
import com.clean.domain.entity.look.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item_receipt",schema = "stc")
@Setter@Getter
@NoArgsConstructor
public class ItemReceipt {
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
    @Column(name = "document_date")
    private Date documentDate;
    @DiffInclude
    @Column(name = "item_receipt_type_id" , nullable = false)
    private short itemReceiptTypeId;
    @DiffInclude
    @Column(name = "receive_date" , nullable = false)
    private Date receiveDate;
    @DiffInclude
    @Column(name = "bill_number")
    private String billNumber;
    @DiffInclude
    @Column(name = "stock_keeper_id" , nullable = false)
    private short stockKeeperId;
    @DiffInclude
    @Column(name = "description",length = 1000)
    private String description;
    @DiffInclude
    @Column(name = "stage_Id")
    private int stageId;
    @DiffInclude
    @Column(name = "status_id")
    private Integer statusId;
    @DiffInclude
    @Column(name = "m16_number" , nullable = false)
    private String m16Number;
    @DiffInclude
    @Column(name = "m3_number" , nullable = false)
    private String m3Number;
    @DiffInclude
    @Column(name ="donor_Id" , nullable = false)
    private int donorId;

    @ManyToOne
    @JoinColumn(columnDefinition = "item_receipt_type_id",referencedColumnName = "id",insertable = false,updatable = false)
    @ToString.Exclude
    private ItemReceiptType itemReceiptType;

    @ManyToOne
    @JoinColumn(columnDefinition = "stock_keeper_id",referencedColumnName = "id",insertable = false,updatable = false)
    @ToString.Exclude
    private StockKeeper stockKeeper;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Donor donor;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "itemReceipt")
    private Set<ItemReceiptDetail> itemReceipts = new HashSet<>();
}
