package com.feedback.vlearning.configuration;

import com.feedback.vlearning.user.Status;
import com.feedback.vlearning.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

public class CustomUserDetails implements UserDetails, Serializable, Comparable<CustomUserDetails> {

    private static final long serialVersionUID = 1L;
    private final Collection<? extends GrantedAuthority> authorities;
    private final User user;

    public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        if (user.getStatus().equals(Status.INACTIVE)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (user.getStatus().equals(Status.ACTIVE)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if (user.getStatus().equals(Status.ACTIVE)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isEnabled() {
        if (user.getStatus().equals(Status.ACTIVE)) {
            return true;
        }
        return false;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomUserDetails) {
            return ((CustomUserDetails) obj).getUser().getId().equals(user.getId());
        }
        return false;
    }

    @Override
    public int compareTo(CustomUserDetails o) {
        return 0;
    }

    @Override
    public String toString() {
        return "user" + user.getId();
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(user.getId() + "");
    }

}
