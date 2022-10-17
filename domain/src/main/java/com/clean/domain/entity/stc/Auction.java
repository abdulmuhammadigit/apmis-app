package com.clean.domain.entity.stc;

import com.clean.domain.entity.conf.FiscalYear;
import com.clean.domain.entity.itm.Item;
import com.clean.domain.entity.itm.ItemDetail;
import com.clean.domain.entity.look.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Auction",schema = "stc")
@Getter
@Setter
@NoArgsConstructor
public class Auction {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private long id;
    @Column(name = "item_specification_id" , nullable = false)
    private Integer itemSpecificationId;
    @Column(name = "item_detail_id" , nullable = false)
    private Integer itemDetailId;
    @DiffInclude
    @Column(name = "quantity" , nullable = false)
    private int quantity;
    @DiffInclude
    @Column(name = "status_id" , nullable = false)
    private Integer statusId;
    @DiffInclude
    @Column(name = "date")
    private Date date;
    @DiffInclude
    @Column(name = "fiscal_year_id")
    private int fiscalYearId;
    @DiffInclude
    @Column(name = "price")
    private float price;
    @DiffInclude
    @Column(name = "pricing_board")
    private float pricingBoard;
    @DiffInclude
    @Column(name = "final_price")
    private float finalPrice;

    @ManyToOne
    @JoinColumn(name = "status_id",insertable = false,updatable = false,referencedColumnName = "id")
    Status status;
    @ManyToOne
    @JoinColumn(name = "item_detail_id",insertable = false,updatable = false,referencedColumnName = "id")
    ItemDetail itemDetail;
    @ManyToOne
    @JoinColumn(name = "fiscal_year_id",insertable = false,updatable = false,referencedColumnName = "id")
    FiscalYear fiscalYear;

    @ManyToOne
    @JoinColumn(name = "item_specification_id",insertable = false,updatable = false,referencedColumnName = "id")
    ItemSpecification itemSpecification;
}
