package com.burundibuhire.burundi.buhire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractEntity implements UserDetails {

    @Column(name = "member_id_number", nullable = false, unique = true) // Not a primary key
    private String memberIdNumber; // Unique identifier for the member

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING) // Store as string in the database
    @Column(name = "gender", nullable = false)
    private GenderEnum gender;

    @OneToMany(mappedBy = "user")
    private List<NationalityMember> nationalityMembers;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "email_verified", nullable = false)
    private Boolean isEmailVerified = false; // Email verification status

    @Column(name = "verification_token",nullable = true)
    private String verificationToken; // Token for email verification

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "whatsapp_number")
    private String whatsappNumber;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_type", nullable = false)
    private MemberTypeEnum memberType;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_grade",nullable = false)
    private MemberGradeEnum memberGrade;

    @ManyToOne
    @JoinColumn(name = "country_of_birth_id",nullable = false)
    private Country countryOfBirth;

    @Column(name = "place_of_birth", nullable = false)
    private String placeOfBirth;


    @Column(name = "passport_photo",nullable = true,columnDefinition = "bytea")
    private byte[] passportPhoto;

    @Column(name = "identity_card_front_side_picture",nullable = true,columnDefinition = "bytea")
    private byte[] identityCardFrontSidePicture;

    @Column(name = "identity_card_back_side_picture",nullable = true,columnDefinition = "bytea")
    private byte[] identityCardBackSidePicture;

    @Column(name = "isuseractive")
    private Boolean isUserActive=true;

    @OneToMany(mappedBy = "user")
    private List<SupportingMember> supportingMembers;

    @OneToMany(mappedBy = "user")
    private List<VolunteerMember> volunteerMembers;

    @OneToMany(mappedBy = "user")
    private List<CandidateMember> candidateMembers;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
