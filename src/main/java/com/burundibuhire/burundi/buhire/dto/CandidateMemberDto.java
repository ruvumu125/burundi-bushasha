package com.burundibuhire.burundi.buhire.dto;

import com.burundibuhire.burundi.buhire.model.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CandidateMemberDto {

    private Long id;
    private Long userId;
    private List<CandidacyFieldCandidateMemberDto> candidacyFieldCandidateMembers;
    private EthnicityEnum ethnicity;
    private List<DegreeDto> degrees;
    private List<ExperienceDto> experience;
    private List<PoliticalHistoryDto> politicalHistories;
    private String passportPhoto; // Base64 string
    private String identityCardFrontSidePicture; // Base64 string
    private String identityCardBackSidePicture; // Base64 string
    private String certificateOfResidence;
    private String certificateOfRegistration;

    // Convert from VolunteerMember entity to VolunteerMemberDto
    public static CandidateMemberDto fromEntity(CandidateMember candidateMember) {
        if (candidateMember == null) {
            return null;
        }

        return CandidateMemberDto.builder()
                .id(candidateMember.getId())
                .userId(candidateMember.getUser() != null ? candidateMember.getUser().getId() : null)
                // Uncomment and implement if you have DTOs for volunteeringAreaMembers, degrees, and experience
//                .volunteeringAreaMembers(volunteerMember.getVolunteeringAreaMembers() != null ?
//                        volunteerMember.getVolunteeringAreaMembers().stream()
//                                .map(VolunteeringAreaVolunteerMemberDto::fromEntity)
//                                .collect(Collectors.toList()) : null)
//
//                .degrees(volunteerMember.getDegrees() != null ?
//                        volunteerMember.getDegrees().stream()
//                                .map(DegreeDto::fromEntity)
//                                .collect(Collectors.toList()) : null)
//
//                .experience(volunteerMember.getExperience() != null ?
//                        volunteerMember.getExperience().stream()
//                                .map(ExperienceDto::fromEntity)
//                                .collect(Collectors.toList()) : null)

//                .passportPhoto(volunteerMember.getPassportPhoto() != null ?
//                        java.util.Base64.getEncoder().encodeToString(volunteerMember.getPassportPhoto()) : null)
//                .identityCardFrontSidePicture(volunteerMember.getIdentityCardFrontSidePicture() != null ?
//                        java.util.Base64.getEncoder().encodeToString(volunteerMember.getIdentityCardFrontSidePicture()) : null)
//                .identityCardBackSidePicture(volunteerMember.getIdentityCardBackSidePicture() != null ?
//                        java.util.Base64.getEncoder().encodeToString(volunteerMember.getIdentityCardBackSidePicture()) : null)
                .build();
    }

    // Convert from VolunteerMemberDto to VolunteerMember entity
    public static CandidateMember toEntity(CandidateMemberDto candidateMemberDto) {
        if (candidateMemberDto == null) {
            return null;
        }

        CandidateMember candidateMember = new CandidateMember();
        candidateMember.setId(candidateMemberDto.getId());

        // Set image fields from Base64 string to byte[]
//        volunteerMember.setPassportPhoto(volunteerMemberDto.getPassportPhoto() != null ?
//                java.util.Base64.getDecoder().decode(volunteerMemberDto.getPassportPhoto()) : null);
//
//        volunteerMember.setIdentityCardFrontSidePicture(volunteerMemberDto.getIdentityCardFrontSidePicture() != null ?
//                java.util.Base64.getDecoder().decode(volunteerMemberDto.getIdentityCardFrontSidePicture()) : null);
//
//        volunteerMember.setIdentityCardBackSidePicture(volunteerMemberDto.getIdentityCardBackSidePicture() != null ?
//                java.util.Base64.getDecoder().decode(volunteerMemberDto.getIdentityCardBackSidePicture()) : null);

        // Uncomment and implement if you have DTOs for volunteeringAreaMembers, degrees, and experience
//        volunteerMember.setVolunteeringAreaMembers(volunteerMemberDto.getVolunteeringAreaMembers() != null ?
//                volunteerMemberDto.getVolunteeringAreaMembers().stream()
//                        .map(VolunteeringAreaVolunteerMemberDto::toEntity)
//                        .collect(Collectors.toList()) : null);
//
//        volunteerMember.setDegrees(volunteerMemberDto.getDegrees() != null ?
//                volunteerMemberDto.getDegrees().stream()
//                        .map(DegreeDto::toEntity)
//                        .collect(Collectors.toList()) : null);
//
//        volunteerMember.setExperience(volunteerMemberDto.getExperience() != null ?
//                volunteerMemberDto.getExperience().stream()
//                        .map(ExperienceDto::toEntity)
//                        .collect(Collectors.toList()) : null);

        return candidateMember;
    }
}

