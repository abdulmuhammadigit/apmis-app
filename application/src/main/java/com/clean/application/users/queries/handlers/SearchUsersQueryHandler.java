package com.clean.application.users.queries.handlers;

import com.clean.application.users.models.UserModel;
import com.clean.application.users.queries.SearchUsersQuery;
import com.clean.common.GeneralResponse;
import com.clean.common.mediator.core.IRequestHandler;
import com.clean.domain.entity.sec.Users;
import com.clean.persistence.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;


public class SearchUsersQueryHandler implements IRequestHandler<SearchUsersQuery, List<UserModel>> {
    private UserRepository userRepository;
    private EntityManager entityManager;

    @Autowired
    public SearchUsersQueryHandler(UserRepository userRepository,EntityManager entityManager) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<UserModel> handle(SearchUsersQuery request) {

//        User ur = new User();
//        ur.setEmail(request.getEmail());
//        ur.setUsername(request.getUsername());
//        ur.setFullName(request.getFullName());
//
//        ExampleMatcher matcher = ExampleMatcher.matchingAll()
//                .withMatcher("username",ExampleMatcher.GenericPropertyMatchers.contains())
//                .withIgnorePaths("id","roles","password");

        //List<User> users = userRepository.findAll(Example.of(ur,matcher));
        //List<User> users = userRepository.findAll(request.getUsername(), request.getEmail(),request.getFullName());
//


        Specification<Users> specification = null;

        if(request.getEmail() != null && !request.getEmail().isEmpty()){
            specification = Specification.where((Specification<Users>) (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"),MessageFormat.format("%{0}%",request.getEmail())));
        }
        if(request.getUsername() != null && !request.getUsername().isEmpty()){
            if(specification == null){
                specification = Specification.where((Specification<Users>) (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("username"),MessageFormat.format("%{0}%",request.getUsername())));
            }
            else{
                specification = specification.and((Specification<Users>) (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("username"),MessageFormat.format("%{0}%",request.getUsername())));
            }
        }
        if(request.getFullName() != null && !request.getFullName().isEmpty()){
            if(specification == null){
                specification = Specification.where((Specification<Users>) (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("fullName"),MessageFormat.format("%{0}%",request.getFullName())));
            }
            else{
                specification = specification.and((Specification<Users>) (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("fullName"), MessageFormat.format("%{0}%",request.getFullName())));
            }
        }

        List<Users> users = specification == null ? userRepository.findAll() : userRepository.findAll(specification);

//        String sql = "select u from User u ";
//        String condition = "";

//        if(request.getEmail() != null && !request.getEmail().isEmpty()){
//            condition  += " u.email like :email ";
//        }
//        if(request.getUsername() != null && !request.getUsername().isEmpty()){
//            condition += (condition.length() > 0 ? " or " : "") +  " u.username like :username ";
//        }
//        if(request.getFullName() != null && !request.getFullName().isEmpty()){
//            condition += (condition.length() > 0 ? " or ":"") + " u.fullName like :fullname ";
//        }
//        if(condition.length() > 0){
//            sql = sql+" where " + condition;
//        }
//
//
//        Query query = entityManager.createQuery(sql);
//
//        if(request.getEmail() != null && !request.getEmail().isEmpty()){
//            query.setParameter("email","%"+request.getEmail()+"%");
//        }
//        if(request.getUsername() != null && !request.getUsername().isEmpty()){
//            query.setParameter("username","%"+ request.getUsername()+"%" );
//        }
//        if(request.getFullName() != null && !request.getFullName().isEmpty()){
//            query.setParameter("fullname","%"+request.getFullName()+"%");
//        }
//
//        List<User> users = query.getResultList();

        return users.stream().map(UserModel::Map).collect(Collectors.toList());
    }
}
