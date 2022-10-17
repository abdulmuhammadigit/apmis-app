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
@Table(name = "password_history", schema = "sec")
public class PasswordHistory {
    @DiffInclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name =  "id",nullable = false)
    private Long id;

    @DiffInclude
    @Basic
    @Column(name =  "user_id",nullable = false)
    private Long userId;

    @DiffInclude
    @Basic
    @Column(name =  "password_hash",nullable = false)
    private String passwordHash;

    @DiffInclude
    @Basic
    @Column(name =  "created_on",nullable = false)
    private Timestamp createdOn;
}
