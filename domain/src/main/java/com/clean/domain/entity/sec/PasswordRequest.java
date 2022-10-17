package com.clean.domain.entity.sec;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffInclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "password_requests", schema = "sec")
public class PasswordRequest {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name =  "id",nullable = false)
    private Long id;

    @DiffInclude
    @Basic
    @Column(name =  "user_id")
    private Long userId;

    @DiffInclude
    @Basic
    @Column(name =  "created_on",nullable = false)
    private Timestamp createdOn;

    @DiffInclude
    @Basic
    @Column(name =  "processed",nullable = false)
    private Boolean processed;

    @DiffInclude
    @Basic
    @Column(name =  "email",nullable = false)
    private String email;
}
