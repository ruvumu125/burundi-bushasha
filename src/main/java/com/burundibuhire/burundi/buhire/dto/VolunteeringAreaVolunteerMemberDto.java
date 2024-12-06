package com.burundibuhire.burundi.buhire.dto;

import com.burundibuhire.burundi.buhire.model.*;
import lombok.Builder;
import lombok.Data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VolunteeringAreaVolunteerMemberDto {

    private Long id;
    private Long volunteerId;
    private VolunteeringAreaDto volunteeringArea;

    // Convert entity to DTO
    public static VolunteeringAreaVolunteerMemberDto fromEntity(VolunteeringAreaVolunteerMember volunteeringAreaVolunteerMember) {
        if (volunteeringAreaVolunteerMember == null) {
            return null;
        }

        return VolunteeringAreaVolunteerMemberDto.builder()
                .id(volunteeringAreaVolunteerMember.getId())
                .volunteerId(volunteeringAreaVolunteerMember.getVolunteerMember().getId())  // Only set the volunteer ID
                .volunteeringArea(VolunteeringAreaDto.fromEntity(volunteeringAreaVolunteerMember.getVolunteeringArea()))
                .build();
    }

    // Convert DTO to entity
    public static VolunteeringAreaVolunteerMember toEntity(VolunteeringAreaVolunteerMemberDto volunteeringAreaVolunteerMemberDto) {
        if (volunteeringAreaVolunteerMemberDto == null) {
            return null;
        }

        VolunteeringAreaVolunteerMember volunteeringAreaVolunteerMember = new VolunteeringAreaVolunteerMember();
        volunteeringAreaVolunteerMember.setId(volunteeringAreaVolunteerMemberDto.getId());

        // Set the VolunteerMember entity by ID
        VolunteerMember volunteerMember = new VolunteerMember();
        volunteerMember.setId(volunteeringAreaVolunteerMemberDto.getVolunteerId());
        volunteeringAreaVolunteerMember.setVolunteerMember(volunteerMember);

        // Set the VolunteeringArea entity by ID
        VolunteeringArea volunteeringArea = new VolunteeringArea();
        volunteeringArea.setId(volunteeringAreaVolunteerMemberDto.getVolunteeringArea().getId());
        volunteeringAreaVolunteerMember.setVolunteeringArea(volunteeringArea);

        return volunteeringAreaVolunteerMember;
    }
}
