package com.burundibuhire.burundi.buhire.dto;

import com.burundibuhire.burundi.buhire.model.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupportingMemberDto {

    private Long id;
    private UserDto user;


    public static SupportingMemberDto fromEntity(SupportingMember supportingMember) {
        if (supportingMember == null) {
            return null;
        }

        return SupportingMemberDto.builder()
                .id(supportingMember.getId())
                .user(UserDto.fromEntity(supportingMember.getUser()))
                .build();
    }

    public static SupportingMember toEntity(SupportingMemberDto supportingMemberDto) {
        if (supportingMemberDto == null) {
            return null;
        }

        SupportingMember supportingMember = new SupportingMember();
        supportingMember.setId(supportingMemberDto.getId());
        supportingMember.setUser(UserDto.toEntity(supportingMemberDto.getUser()));

        return supportingMember;
    }
}
