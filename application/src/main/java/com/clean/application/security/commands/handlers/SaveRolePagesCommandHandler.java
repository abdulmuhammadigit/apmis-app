package com.clean.application.security.commands.handlers;

import com.clean.application.security.commands.SaveRolePagesCommand;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.sec.RolePage;
import com.clean.persistence.sec.RolePageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class SaveRolePagesCommandHandler implements IRequestHandler<SaveRolePagesCommand,Boolean> {
    private RolePageRepository rolePageRepository;

    @Autowired
    public SaveRolePagesCommandHandler(RolePageRepository rolePageRepository){
        this.rolePageRepository = rolePageRepository;
    }

    @Transactional
    @Override
    public Boolean handle(SaveRolePagesCommand request) {
        List<RolePage> pages = rolePageRepository.findAllByRoleId(request.getRoleId());
        List<Integer> pageIds = pages.stream().map(RolePage::getPageId).collect(Collectors.toList());
        List<RolePage> toRemove = pages.stream().filter(c -> !request.getPageIds().contains(c.getPageId())).collect(Collectors.toList());
        List<Integer> toCreate = request.getPageIds().stream().filter( c -> !pageIds.contains(c)).collect(Collectors.toList());

        rolePageRepository.deleteAll(toRemove);
        for (Integer pageId : toCreate) {
            rolePageRepository.save(
                    RolePage.builder()
                        .pageId(pageId)
                        .roleId(request.getRoleId())
                        .build()
            );
        }
        return true;
    }
}
