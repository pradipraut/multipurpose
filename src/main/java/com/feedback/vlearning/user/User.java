package com.feedback.vlearning.user;

import com.feedback.vlearning.abstractclass.AbstractClass;
import com.feedback.vlearning.configuration.Authority;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User extends AbstractClass<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = true)
    private String password;

    private Status status;

    private String email;

    private UserType
            userType;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /*@Override
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
    }*/

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorites = new HashSet<>();
        authorites.add(new Authority(userType.name()));
        return authorites;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
