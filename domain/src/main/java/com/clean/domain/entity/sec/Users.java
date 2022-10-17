package com.clean.domain.entity.sec;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;
import javax.persistence.*;

import com.clean.domain.entity.prc.Stage;
import org.javers.core.metamodel.annotation.DiffInclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@javax.persistence.Entity
@Table(name = "users", schema = "sec")
public class Users implements Serializable {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "id", nullable = false)
    private Long id;

    @DiffInclude
    @Basic
    @Column(name = "email")
    private String email;

    @DiffInclude
    @Basic
    @Column(name = "password")
    private String password;

    @DiffInclude
    @Basic
    @Column(name = "username")
    private String username;

    @DiffInclude
    @Basic
    @Column(name = "full_name")
    private String fullName;

    @DiffInclude
    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "institution_id")
    private Long institutionId;

    @DiffInclude
    @Basic
    @Column(name = "operation_type_id")
    private Integer operationTypeId;

    @DiffInclude
    @Basic
    @Column(name = "is_admin")
    private Boolean isAdmin;

    @DiffInclude
    @Basic
    @Column(name = "password_expired")
    private Boolean passwordExpired;

    @DiffInclude
    @Basic
    @Column(name = "is_disabled")
    private Boolean isDisabled;

    @DiffInclude
    @Basic
    @Column(name = "password_changed_date")
    private Timestamp passwordChangedDate;

    @DiffInclude
    @Basic
    @Column(name = "created_on")
    private Timestamp createdOn;

    @DiffInclude
    @Basic
    @Column(name = "created_by")
    private Long createdBy;

    @DiffInclude
    @Basic
    @Column(name = "modified_on")
    private Timestamp modifiedOn;

    @DiffInclude
    @Basic
    @Column(name = "modified_by")
    private Long modifiedBy;

    @DiffInclude
    @Basic
    @Column(name = "last_login_date")
    private Timestamp lastLoginDate;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_stage",schema = "sec",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "stage_id"))
    private Set<Stage> stages = new HashSet<>();

}
