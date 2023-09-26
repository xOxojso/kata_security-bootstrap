package ru.kata.spring.boot_security_bootstrap.demo.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security_bootstrap.demo.entity.User;

import java.util.Collection;

public class UserDetailsImp implements UserDetails {
    private final User user;

    public UserDetailsImp(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public boolean hasRole(String roleName) {
        return this.user.hasRole(roleName);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
