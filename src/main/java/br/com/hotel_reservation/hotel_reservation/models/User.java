package br.com.hotel_reservation.hotel_reservation.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {

    public User(String name, String email, String pass, List<Profile> profiles) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.profiles = profiles;
    }

    private Long id = null;
    private String name;

    @Pattern(regexp = "^([\\w\\d][-._]?)+@([\\w\\d]?)+\\.([\\w\\d])+.?$")
    private String email;
    private String pass;
    private List<Profile> profiles = new ArrayList();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.profiles;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    @Override
    public String getPassword() {
        return this.pass;
    }

    @Override
    public String getUsername() {
        return this.email;
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
