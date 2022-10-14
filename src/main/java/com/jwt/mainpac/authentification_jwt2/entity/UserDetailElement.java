package com.jwt.mainpac.authentification_jwt2.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailElement implements UserDetails {

    private Collection<GrantedAuthority> authorities; //отображает разрешения данные пользователю
    private String password;
    private String userName;
    private Boolean active;

    public UserDetailElement(Collection<GrantedAuthority> authorities, String password, String userName, Boolean active) {
        this.authorities = authorities;
        this.password = password;
        this.userName = userName;
        this.active = active;
    }

    public UserDetailElement() {
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
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
