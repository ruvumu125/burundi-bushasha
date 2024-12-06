package com.burundibuhire.burundi.buhire.dto;

import com.burundibuhire.burundi.buhire.model.*;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import java.util.stream.Collectors;

@Data
@Builder
public class UserDto {

    private Long id;
    private String memberIdNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private GenderEnum gender;
    private List<NationalityMemberDto> nationalityMembers;
    private String email;
    private Boolean isEmailVerified;
    private String phoneNumber;
    private String whatsappNumber;
    private String username;
    private String password;
    private String confirmPassword;
    private String verificationToken; // Added verification token field
    private MemberTypeEnum memberType;
    private MemberGradeEnum memberGrade;
    private CountryDto countryOfBirth;
    private String placeOfBirth;
    private Boolean isUserActive;

    public static UserDto fromEntity(User user) {
        if (user == null) {
            return null;
        }
        return null;

//        return UserDto.builder()
//                .id(user.getId())
//                .memberIdNumber(user.getMemberIdNumber())
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .dateOfBirth(user.getDateOfBirth())
//                .gender(user.getGender())
//                .nationalityMembers(user.getNationalityMembers() != null ?
//                        user.getNationalityMembers().stream()
//                                .map(NationalityMemberDto::fromEntity)
//                                .collect(Collectors.toList()) : null)
//                .email(user.getEmail())
//                .isEmailVerified(user.getIsEmailVerified())
//                .phoneNumber(user.getPhoneNumber())
//                .whatsappNumber(user.getWhatsappNumber())
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .verificationToken(user.getVerificationToken()) // Mapping verification token
//                .memberType(user.getMemberType())
//                .memberGrade(user.getMemberGrade())
//                .countryOfBirth(user.getCountryOfBirth() != null ? CountryDto.fromEntity(user.getCountryOfBirth()) : null)
//                .placeOfBirth(user.getPlaceOfBirth())
//                .isUserActive(user.getIsUserActive())
//                .build();
    }

    public static User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();
        user.setMemberIdNumber(userDto.getMemberIdNumber());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setGender(userDto.getGender());
        user.setNationalityMembers(userDto.getNationalityMembers() != null ?
                userDto.getNationalityMembers().stream()
                        .map(NationalityMemberDto::toEntity)
                        .collect(Collectors.toList()) : null);
        user.setEmail(userDto.getEmail());
        user.setIsEmailVerified(userDto.getIsEmailVerified());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setWhatsappNumber(userDto.getWhatsappNumber());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword()); // Mapping password
        user.setVerificationToken(userDto.getVerificationToken()); // Mapping verification token
        user.setMemberType(userDto.getMemberType());
        user.setMemberGrade(userDto.getMemberGrade());

        if (userDto.getCountryOfBirth() != null) {
            user.setCountryOfBirth(CountryDto.toEntity(userDto.getCountryOfBirth()));
        }

        user.setPlaceOfBirth(userDto.getPlaceOfBirth());
        user.setIsUserActive(userDto.getIsUserActive());

        return user;
    }
}
