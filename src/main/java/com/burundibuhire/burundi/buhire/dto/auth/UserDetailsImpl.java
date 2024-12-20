package com.burundibuhire.burundi.buhire.dto.auth;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.burundibuhire.burundi.buhire.model.*;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    private final Long id;
    private String memberIdNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private GenderEnum gender;
    private String email;
    private Boolean isEmailVerified;
    private String verificationToken;
    private String phoneNumber;
    private String whatsappNumber;
    private String nomUtilisateur;
    private String password;
    private MemberTypeEnum memberType;
    private MemberGradeEnum memberGrade;
    private Country countryOfBirth;
    private String placeOfBirth;
    private Boolean isUserActive;
    private final Set<GrantedAuthority> authorities;

    public UserDetailsImpl(User user) {
        this.id = user.getId();
        this.memberIdNumber=user.getMemberIdNumber();
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.dateOfBirth=user.getDateOfBirth();
        this.gender=user.getGender();
        this.email=user.getEmail();
        this.isEmailVerified=user.getIsEmailVerified();
        this.verificationToken=user.getVerificationToken();
        this.phoneNumber=user.getPhoneNumber();
        this.whatsappNumber=user.getWhatsappNumber();
        this.nomUtilisateur=user.getNomUtilisateur();
        this.password=user.getPassword();
        this.memberType=user.getMemberType();
        this.memberGrade=user.getMemberGrade();
        this.countryOfBirth=user.getCountryOfBirth();
        this.placeOfBirth=user.getPlaceOfBirth();
        this.isUserActive=user.getIsUserActive();
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
        return null;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
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
        return isUserActive;
    }

    // Setter methods for additional details


    public Long getId() {
        return id;
    }

    public String getMemberIdNumber() {
        return memberIdNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public MemberTypeEnum getMemberType() {
        return memberType;
    }

    public MemberGradeEnum getMemberGrade() {
        return memberGrade;
    }

    public Country getCountryOfBirth() {
        return countryOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public Boolean getUserActive() {
        return isUserActive;
    }
}

