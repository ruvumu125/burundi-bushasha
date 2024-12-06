package com.burundibuhire.burundi.buhire.controller;

import com.burundibuhire.burundi.buhire.controller.api.MemberApi;
import com.burundibuhire.burundi.buhire.dto.CandidateMemberDto;
import com.burundibuhire.burundi.buhire.dto.UserDto;
import com.burundibuhire.burundi.buhire.dto.VolunteerMemberDto;
import com.burundibuhire.burundi.buhire.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController implements MemberApi {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @Override
    public UserDto save(UserDto dto) {
        return memberService.saveSupportingMember(dto);
    }

    @Override
    public VolunteerMemberDto upgradeToVolunteerMember(VolunteerMemberDto dto) {
        return memberService.upgradeToVolontaire(dto);
    }

    @Override
    public CandidateMemberDto upgradeToCandidate(CandidateMemberDto dto) {
        return memberService.upgradeToCandidate(dto);
    }

    @Override
    public boolean verifyUserEmail(String token) {
        return memberService.verifyToken(token);
    }


}
