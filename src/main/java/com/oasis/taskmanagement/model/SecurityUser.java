package com.oasis.taskmanagement.model;

import com.oasis.taskmanagement.enums.Roles;
import com.oasis.taskmanagement.enums.UserPermissions;
import com.oasis.taskmanagement.exception.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class SecurityUser implements UserDetails {
    private final UserEntity userEntity;
    public SecurityUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (userEntity.getRoles() == null) {
            throw new AuthenticationException("User has no privileges");
        }
        return Arrays.stream( userEntity.getRoles()
                        .split(","))
                .map(Roles::valueOf)
                .map(Roles::getPermissions)
                .flatMap(Collection::stream)
                .map(UserPermissions::getPermission)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
