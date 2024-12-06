package com.burundibuhire.burundi.buhire.dto;

import com.burundibuhire.burundi.buhire.model.NationalityMember;
import com.burundibuhire.burundi.buhire.model.User;
import lombok.Builder;
import lombok.Data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NationalityMemberDto {
    private Long id;
    private Long userId; // Reference to the user by ID
    private NationalityDto nationality;

    public static NationalityMemberDto fromEntity(NationalityMember nationalityMember) {
        if (nationalityMember == null) {
            return null;
        }

        return NationalityMemberDto.builder()
                .id(nationalityMember.getId())
                .userId(nationalityMember.getUser().getId()) // Only set the user ID
                .nationality(NationalityDto.fromEntity(nationalityMember.getNationality()))
                .build();
    }

    public static NationalityMember toEntity(NationalityMemberDto nationalityMemberDto) {
        if (nationalityMemberDto == null) {
            return null;
        }

        NationalityMember nationalityMember = new NationalityMember();
        nationalityMember.setId(nationalityMemberDto.getId());

        User user = new User();
        user.setId(nationalityMemberDto.getUserId()); // Only set the user ID
        nationalityMember.setUser(user);

        nationalityMember.setNationality(NationalityDto.toEntity(nationalityMemberDto.getNationality()));

        return nationalityMember;
    }
}
