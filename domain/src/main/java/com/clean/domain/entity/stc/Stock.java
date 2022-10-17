package com.clean.domain.entity.stc;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javers.core.metamodel.annotation.DiffInclude;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stock",schema = "stc")
public class Stock{
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,nullable = false)
    private long id;
    @DiffInclude
    @Column(name = "item_detail_id",nullable = false)
    private int itemDetailId;
    @DiffInclude
    @Column(name = "date",nullable = false)
    private Date date;
    @DiffInclude
    @Column(name = "type_id",nullable = false)
    private int typeId;
    @DiffInclude
    @Column(name = "item_receipt_id",nullable = false)
    private long itemReceiptId;
    @DiffInclude
    @Column(name = "price" , nullable = false)
    private double price;
}
