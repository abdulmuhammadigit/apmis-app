package com.clean.domain.entity.stc;

import com.clean.domain.entity.hr.Employee;
import com.clean.domain.entity.look.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javers.core.metamodel.annotation.DiffInclude;

import javax.persistence.*;

@Entity
@Table(name = "item_reallocation_specification",schema = "stc")
@Getter
@Setter
@NoArgsConstructor
public class ItemReallocationSpecification {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private long id;
    @DiffInclude
    @Column(name="item_reallocation_detail_id", nullable = false)
    private long itemReallocationDetailId;
    @DiffInclude
    @Column(name="item_distributed_specification_id" , nullable = false)
    private long itemDistributedSpecificationId;
    @DiffInclude
    @Column(name="examiner_board")
    private String examinerBoard;
    @DiffInclude
    @Column(name = "status_id" , nullable = false)  
    private int statusId;
    @DiffInclude
    @Column(name = "board_status_id")
    private int boardStatusId;
    @DiffInclude
    @Column(name = "price")
    private float price;
    @DiffInclude
    @Column(name = "to_employee_id",nullable = true)
    private Integer toEmployeeId;  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_employee_id",referencedColumnName = "id",insertable = false,updatable = false)
    Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_reallocation_detail_id",referencedColumnName = "id",insertable = false,updatable = false)
    ItemReallocationDetail itemReallocationDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id",referencedColumnName = "id",insertable = false,updatable = false)
    Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_distributed_specification_id",referencedColumnName = "id",insertable = false,updatable = false)
    ItemDistributedSpecification itemDistributedSpecification;
}
