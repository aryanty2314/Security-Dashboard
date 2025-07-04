package com.security.dashboard.auth.entity;

import com.security.dashboard.auth.Roles;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

// This class represents a User entity in the application.
public class User implements UserDetails
{

    @Id
    private int id;

    private String name;
    private String email;
    private String password;
    private Roles role;
    private boolean twoFactorEnabled;
    private boolean accountNonLocked;
    private boolean enabled = true;


    // This method returns the password of the user.


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
}
