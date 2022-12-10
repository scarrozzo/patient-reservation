package com.patient.reservation.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.patient.reservation.domain.user.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private final Long id;

    private final String username;

    @JsonIgnore
    private final String password;

    private Boolean isEnabled;

    private Boolean isDoctor;

    private Boolean isPatient;

    private String uid;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(
            Long id, String username, String password, Boolean isEnabled, String uid, Boolean isDoctor, Boolean isPatient,
            Collection<? extends GrantedAuthority> authorities
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.uid = uid;
        this.isDoctor = isDoctor;
        this.isPatient = isPatient;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getPasswordHash(),
                user.getLoginEnabled(),
                user.getUid(),
                user.getIsDoctor(),
                user.getIsPatient(),
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
        return isEnabled;
    }

    public Long getId(){
        return id;
    }

    public String getUid() {
        return uid;
    }

    public Boolean isDoctor(){
        return isDoctor;
    }

    public Boolean isPatient(){
        return isPatient;
    }
}
