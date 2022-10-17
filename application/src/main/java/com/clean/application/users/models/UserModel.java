package com.clean.application.users.models;

import java.util.List;

import com.clean.domain.entity.sec.Users;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserModel {
    private Long id;
    private String email;
    private String username;
    private String fullName;
    private String phone;
    private Long institutionId;
    private Integer operationTypeId;
    private Boolean isAdmin;
    private Boolean passwordExpired;
    private Boolean disabled;

    private List<Integer> roleIds;
    private List<Integer> stageIds;
    
    public static UserModel Map(Users entity){
        return UserModel.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .fullName(entity.getFullName())
                .phone(entity.getPhone())
                .institutionId(entity.getInstitutionId())
                .operationTypeId(entity.getOperationTypeId())
                .isAdmin(entity.getIsAdmin())
                .passwordExpired(entity.getPasswordExpired())
                .disabled(entity.getIsDisabled())
                .build();
    }
}
