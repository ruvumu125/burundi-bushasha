package com.burundibuhire.burundi.buhire.dto.auth;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.burundibuhire.burundi.buhire.model.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String nomUtilisateur;
    private final String phoneNumber;
    private final String whatsappNumber;
    private final String memberIdNumber;
    private final String userrole;
    private final boolean isuseractive;
    private final Set<GrantedAuthority> authorities;

    public UserDetailsImpl(User user) {
        this.id = user.getId();
        this.username = user.getEmail();
        this.userrole = user.getMemberType().toString();
        this.isuseractive = user.getIsUserActive();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.gender = user.getGender().toString();
        this.nomUtilisateur = user.getNomUtilisateur();
        this.phoneNumber = user.getPhoneNumber();
        this.whatsappNumber = user.getWhatsappNumber();
        this.memberIdNumber = user.getMemberIdNumber();
        this.authorities = new HashSet<>();
        this.authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getMemberType().toString().toUpperCase()));
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
        return isuseractive;
    }

    // Setter methods for additional details

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public String getMemberIdNumber() {
        return memberIdNumber;
    }

    public String getUserrole() {
        return userrole;
    }

    public boolean isIsuseractive() {
        return isuseractive;
    }
}

