package com.clean.application.users.queries.handlers;

import com.clean.application.configuration.models.ModuleModel;
import com.clean.application.configuration.models.PageModel;
import com.clean.application.services.UserService;
import com.clean.application.users.queries.UserModulesQuery;
import com.clean.common.GeneralResponse;
import com.clean.common.exceptions.EntityNotFoundException;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.conf.Modules;
import com.clean.domain.entity.conf.Pages;
import com.clean.persistence.configuration.ModulesRepository;
import com.clean.persistence.configuration.PagesRepository;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class GetModuleListQueryHandler implements IRequestHandler<UserModulesQuery, List<ModuleModel>> {
    private ModulesRepository moduleRepository;
    private PagesRepository pagesRepository;
    private EntityManager entityManager;
    private UserService userService;

    public GetModuleListQueryHandler(ModulesRepository moduleRepository, PagesRepository pagesRepository,
            EntityManager entityManager, UserService userService) {
        this.moduleRepository = moduleRepository;
        this.pagesRepository = pagesRepository;
        this.entityManager = entityManager;
        this.userService = userService;
    }

    @Override
    public List<ModuleModel> handle(UserModulesQuery request) {
        List<ModuleModel> modules;
        if (!userService.GetCurrentUser().getIsAdmin()) {
            Query query = entityManager
                    .createNativeQuery("with recursive records as (" + "    select p.* from sec.user_roles ur "
                            + "    inner join sec.role_page rp on ur.role_id = rp.role_id "
                            + "    inner join conf.pages p on rp.page_id = p.id " + "    where ur.user_id = :userId"
                            + "    union " + "    select p2.* from conf.pages p2 "
                            + "    inner join records r on r.parent_id = p2.id" + ")"
                            + "select  * from records order by sorter", Pages.class);
            query.setParameter("userId", userService.getUserId());

            List<Pages> pages = query.getResultList();

            List<Pages> modulePages = new ArrayList<>();
            List<Pages> chilList = new ArrayList<>();
            pages.forEach(cur -> {
                if (cur.getModuleId() != null) {
                    modulePages.add(cur);
                } else {
                    chilList.add(cur);
                }
            });
            modules = moduleRepository
                    .findAllById(modulePages.stream().map(c -> c.getModuleId()).collect(Collectors.toList())).stream()
                    .map(ModuleModel::Map).collect(Collectors.toList());

            modules.sort(Comparator.comparing(ModuleModel::getSorter));
            modulePages.forEach(cur -> {
                ModuleModel module = modules.stream().filter(e -> e.getId() == cur.getModuleId()).findFirst()
                        .orElseThrow(() -> new EntityNotFoundException("Module"));
                if (module.getMenus() == null) {
                    module.setMenus(new ArrayList<>());
                }
                module.getMenus().add(LoadPageChilds(cur, chilList));
            });
        } else {
            modules = moduleRepository.findAll(Sort.by(Sort.Direction.ASC, "sorter")).stream().map(ModuleModel::FullMap)
                    .collect(Collectors.toList());
        }
        return modules;
    }

    private PageModel LoadPageChilds(Pages cur, List<Pages> chilList) {
        PageModel model = PageModel.Map(cur);
        model.setSubmenu(new ArrayList<>());
        chilList.stream().filter(c -> c.getParentId() == cur.getId()).forEach(el -> {
            model.getSubmenu().add(LoadPageChilds(el, chilList));
        });
        return model;
    }
}
