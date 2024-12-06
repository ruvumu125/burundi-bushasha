package com.burundibuhire.burundi.buhire.dto;

import com.burundibuhire.burundi.buhire.model.CandidacyField;
import com.burundibuhire.burundi.buhire.model.CandidacyFieldCandidateMember;
import com.burundibuhire.burundi.buhire.model.CandidateMember;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidacyFieldCandidateMemberDto {

    private Long id;
    private Long candidateId;
    private CandidacyFieldDto candidacyField;

    // Convert entity to DTO
    public static CandidacyFieldCandidateMemberDto fromEntity(CandidacyFieldCandidateMember candidacyFieldCandidateMember) {
        if (candidacyFieldCandidateMember == null) {
            return null;
        }

        return CandidacyFieldCandidateMemberDto.builder()
                .id(candidacyFieldCandidateMember.getId())
                .candidateId(candidacyFieldCandidateMember.getCandidateMember().getId())  // Only set the volunteer ID
                .candidacyField(CandidacyFieldDto.fromEntity(candidacyFieldCandidateMember.getCandidacyField()))
                .build();
    }

    // Convert DTO to entity
    public static CandidacyFieldCandidateMember toEntity(CandidacyFieldCandidateMemberDto candidacyFieldCandidateMemberDto) {
        if (candidacyFieldCandidateMemberDto == null) {
            return null;
        }

        CandidacyFieldCandidateMember candidacyFieldCandidateMember = new CandidacyFieldCandidateMember();
        candidacyFieldCandidateMember.setId(candidacyFieldCandidateMemberDto.getId());

        // Set the VolunteerMember entity by ID
        CandidateMember candidateMember = new CandidateMember();
        candidateMember.setId(candidacyFieldCandidateMemberDto.getCandidateId());
        candidacyFieldCandidateMember.setCandidateMember(candidateMember);

        // Set the VolunteeringArea entity by ID
        CandidacyField candidacyField= new CandidacyField();
        candidacyField.setId(candidacyFieldCandidateMemberDto.getCandidacyField().getId());
        candidacyFieldCandidateMember.setCandidacyField(candidacyField);

        return candidacyFieldCandidateMember;
    }

}
