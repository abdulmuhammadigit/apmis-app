package com.clean.domain.entity.hr;

import com.clean.domain.entity.stc.ItemReceipt;
import com.clean.domain.entity.look.Category;
import com.clean.domain.entity.look.Location;
import com.clean.domain.entity.look.StockKeeperType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "stock_keeper",schema = "hr")
@NoArgsConstructor
public class StockKeeper {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private short id;
    @DiffInclude
    @Column(name = "employee_id" , nullable = false)
    private int employeeId;
    @DiffInclude
    @Column(name = "stock_keeper_type_id" , nullable = false)
    private short stockKeeperTypeId;
    @DiffInclude
    @Column(name = "item_category_id")
    private short itemCategoryId;
    @DiffInclude
    @Column(name = "location_id")
    private Integer locationId;
    @DiffInclude
    @Column(name = "user_id",nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id",referencedColumnName = "id",insertable = false,updatable = false)
    Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_keeper_type_id",referencedColumnName = "id",insertable = false,updatable = false)
    StockKeeperType stockKeeperType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_category_id",referencedColumnName = "id",insertable =false ,updatable = false)
    Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id",referencedColumnName = "id",insertable = false,updatable = false)
    Location location;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "stockKeeper")
    private Set<ItemReceipt> itemReceipts = new HashSet<>();
}
