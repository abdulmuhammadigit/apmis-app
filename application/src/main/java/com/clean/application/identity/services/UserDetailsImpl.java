package com.clean.application.identity.services;

import com.clean.domain.entity.sec.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Data
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String fullName;

    private String username;

    private String email;

    private Long institutionId;
    private Integer operationTypeId;
    private Boolean isAdmin;
    private Boolean passwordExpired;
    private Boolean disabled;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String email, String password,String fullName,
                            Long institutionId,Integer operationTypeId,Boolean isAdmin,Boolean passwordExpired,Boolean disabled,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.authorities = authorities;
        this.institutionId = institutionId;
        this.operationTypeId = operationTypeId;
        this.isAdmin = isAdmin;
        this.passwordExpired = passwordExpired;
        this.disabled = disabled;
    }

    public static UserDetailsImpl build(Users user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getFullName(),
                user.getInstitutionId(),
                user.getOperationTypeId(),
                user.getIsAdmin(),
                user.getPasswordExpired(),
                user.getIsDisabled(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return disabled == null ? true : !disabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return disabled == null ? true : !disabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return passwordExpired == null ? true : !passwordExpired;
    }

    @Override
    public boolean isEnabled() {
        return disabled == null ? true : !disabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

}
