package com.clean.application.identity.commands.handlers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.clean.application.identity.commands.CreateUserCommand;
import com.clean.application.services.UserService;
import com.clean.application.users.models.UserModel;
import com.clean.common.AppConfig;
import com.clean.common.exceptions.EntityNotFoundException;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.sec.Roles;
import com.clean.domain.entity.sec.UserRoles;
import com.clean.domain.entity.sec.Users;
import com.clean.domain.entity.sec.UserStage;
import com.clean.persistence.auth.UserRepository;
import com.clean.persistence.auth.UserRolesRepository;
import com.clean.persistence.sec.UserStageRepository;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;



public class CreateUserCommandHandler implements IRequestHandler<CreateUserCommand, UserModel> {

    
    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private UserService userService;
    private UserRolesRepository userRolesRepository;
    private UserStageRepository userStageRepository;

    @Autowired
    public CreateUserCommandHandler(UserRepository userRepository,PasswordEncoder encoder,UserService userService,UserRolesRepository userRolesRepository,UserStageRepository userStageRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.userService = userService;
        this.userRolesRepository = userRolesRepository;
        this.userStageRepository = userStageRepository;
    }

    @SneakyThrows
    @Override
    public UserModel handle(CreateUserCommand request) {
        Users user = null;
        if(request.getId() != null){
            user =  userRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("User",request.getId()));

            Users target =   userRepository.findByUsername(request.getUsername()).orElse(null);
            Users targetEmail =  userRepository.findByEmail(request.getEmail()).orElse(null);
            if(target != null && target.getId() != user.getId()){
                throw new RuntimeException("Error: Username is already taken!");
            }
            if(targetEmail != null && targetEmail.getId() != user.getId()){
                throw new RuntimeException("Error: Email is already in use!");
            }

            user.setModifiedBy(userService.getUserId());
            user.setModifiedOn(Timestamp.from(Instant.now()));            
        }
        else{

            if (userRepository.existsByUsername(request.getUsername())) {
                throw new RuntimeException("Error: Username is already taken!");
            }

            if (userRepository.existsByEmail(request.getEmail())) {
                throw new RuntimeException("Error: Email is already in use!");
            }

            // Create new user's account

            user = Users.builder()
                    .createdBy(userService.getUserId())
                    .createdOn(Timestamp.from(Instant.now()))
                    .password(encoder.encode(AppConfig.DEFAULT_PASSWORD))
                    .build();    
        }

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setInstitutionId(request.getInstitutionId());
        user.setOperationTypeId(request.getOperationTypeId());
        user.setIsAdmin(request.getIsAdmin());
        user.setPasswordExpired(request.getPasswordExpired());
        user.setIsDisabled(request.getDisabled());
        
        userRepository.save(user);

        Long userId = user.getId();

        List<UserRoles> userRoles = userRolesRepository.findAllByUserId(userId);

        List<UserRoles> toDelete = userRoles.stream().filter(cur -> !request.getRoleIds().contains(cur.getRoleId())).collect(Collectors.toList());

        List<Integer> roleIds = userRoles.stream().map(cur -> cur.getRoleId()).collect(Collectors.toList());

        List<Integer> toCreate = request.getRoleIds().stream().filter(cur -> !roleIds.contains(cur)).collect(Collectors.toList());

        userRolesRepository.deleteAll(toDelete);
        toCreate.forEach(cur -> {
            userRolesRepository.save(
                UserRoles.builder()
                    .userId(userId)
                    .roleId(cur)
                    .build()
            );
        });

        List<UserStage> userStages = userStageRepository.findAllByUserId(userId);

        List<UserStage> toDeleteStages = userStages.stream().filter(cur -> !request.getStageIds().contains(cur.getStageId())).collect(Collectors.toList());

        List<Integer> stageIds = userStages.stream().map(cur -> cur.getStageId()).collect(Collectors.toList());

        List<Integer> toCreateStages = request.getStageIds().stream().filter(cur -> !stageIds.contains(cur)).collect(Collectors.toList());

        userStageRepository.deleteAll(toDeleteStages);
        toCreateStages.forEach(cur -> {
            userStageRepository.save(
                UserStage.builder()
                .stageId(cur)
                .userId(userId)
                .build()
            );
        });

        UserModel model = UserModel.Map(user);
        model.setRoleIds(request.getRoleIds());
        model.setStageIds(request.getStageIds());
        return model;
    }
}
