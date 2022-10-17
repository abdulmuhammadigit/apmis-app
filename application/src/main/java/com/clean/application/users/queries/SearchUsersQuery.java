package com.clean.application.users.queries;

import com.clean.application.users.models.UserModel;
import com.clean.common.GeneralResponse;
import com.clean.common.mediator.core.IRequest;
import lombok.Data;

import java.util.List;

@Data
public class SearchUsersQuery implements IRequest<List<UserModel>> {
    private String username;
    private String fullName;
    private String email;
}
