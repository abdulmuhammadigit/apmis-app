package com.clean.application.identity.commands;

import java.util.List;

import com.clean.application.users.models.UserModel;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

@Data
public class CreateUserCommand implements IRequest<UserModel> {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String phone;
    private Long institutionId;
    private Integer operationTypeId;
    private Boolean isAdmin;
    private Boolean passwordExpired;
    private Boolean disabled;
    private List<Integer> roleIds;
    private List<Integer> stageIds;

}
