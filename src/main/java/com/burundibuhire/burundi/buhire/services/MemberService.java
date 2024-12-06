package com.burundibuhire.burundi.buhire.services;

import com.burundibuhire.burundi.buhire.dto.CandidateMemberDto;
import com.burundibuhire.burundi.buhire.dto.UserDto;
import com.burundibuhire.burundi.buhire.dto.VolunteerMemberDto;

public interface MemberService {

    UserDto saveSupportingMember(UserDto userDto);
    VolunteerMemberDto upgradeToVolontaire(VolunteerMemberDto volunteerMemberDto);
    CandidateMemberDto upgradeToCandidate(CandidateMemberDto candidateMemberDto);
    boolean verifyToken(String token);
}