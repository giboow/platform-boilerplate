package com.giboow.boilerplate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.giboow.boilerplate.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Data
@Table(name = "\"user\"")
@NoArgsConstructor
@AllArgsConstructor

public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * User email, use as login
     */
    @NonNull
    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    /**
     * User password
     */
    @JsonIgnore
    private String password;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @NonNull
    @NotBlank
    private String firstName;

    @NonNull
    @NotBlank
    private String lastName;

    private boolean active = true;

    @JsonIgnore()
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(role.name().toUpperCase()));
    }

    @JsonIgnore()
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore()
    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore()
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @JsonIgnore()
    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @JsonIgnore()
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @JsonIgnore()
    @Override
    public boolean isEnabled() {
        return active;
    }

    /**
     * Allow to set password
     * @param password
     */
    @JsonProperty("password")
    public void setPassword(@NonNull String password) {
        this.password = password;
    }

}
