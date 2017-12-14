package org.itglance.docsea.jwt;

import org.itglance.docsea.domain.Status;
import org.itglance.docsea.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class JwtUser extends User implements UserDetails {

    private final Long id;

    private final String username;

    private final String password;

    private final Status status;

    private final int userType;

    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(User user) {
        this.authorities = getGrantedAuthorities();
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.status = user.getStatus();
        this.userType = user.getUserType();
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities() {
        Collection<SimpleGrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("ROLE_"+Integer.toString(getUserType())));
        return roles;
    }

//    private final Date lastPasswordResetDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public int getUserType() {
        return userType;
    }
}
