package com.clean.application.users.queries;

import com.clean.application.users.models.UserModel;
import com.clean.common.mediator.core.IRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindUserQuery implements IRequest<UserModel> {
    @NotNull
    @NotBlank
    private Long id;
}
