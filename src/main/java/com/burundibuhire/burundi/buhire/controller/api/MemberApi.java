package com.burundibuhire.burundi.buhire.controller.api;

import com.burundibuhire.burundi.buhire.dto.CandidateMemberDto;
import com.burundibuhire.burundi.buhire.dto.UserDto;
import com.burundibuhire.burundi.buhire.dto.VolunteerMemberDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.burundibuhire.burundi.buhire.utils.Constants;

@RequestMapping("/members")
public interface MemberApi {

    @Operation(summary = "Enregistrer un membre sympathisant", description = "Cette methode permet d'enregistrer un membre sympathisant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet membre cree / modifie"),
            @ApiResponse(responseCode = "400", description = "L'objet admin n'est pas valide")
    })
    @PostMapping(value = Constants.APP_ROOT + "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto save(@RequestBody UserDto dto);


    @Operation(summary = "Enregistrer un membre volontaire", description = "Cette methode permet d'enregistrer un membre volontaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet membre cree / modifie"),
            @ApiResponse(responseCode = "400", description = "L'objet admin n'est pas valide")
    })
    @PostMapping(value = Constants.APP_ROOT + "/volontaire", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    VolunteerMemberDto upgradeToVolunteerMember(@RequestBody VolunteerMemberDto dto);

    @Operation(summary = "Enregistrer un membre candidat", description = "Cette methode permet d'enregistrer un membre candidat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet membre cree / modifie"),
            @ApiResponse(responseCode = "400", description = "L'objet admin n'est pas valide")
    })
    @PostMapping(value = Constants.APP_ROOT + "/candidate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CandidateMemberDto upgradeToCandidate(@RequestBody CandidateMemberDto dto);

    @Operation(summary = "Vérifier email d'un membre sympathisant", description = "Cette methode permet de vérifier email d'un membre sympathisant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Verification successful!"),
            @ApiResponse(responseCode = "400", description = "Invalid or expired token.")
    })
    @GetMapping(value = Constants.APP_ROOT + "/verify/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean verifyUserEmail(@PathVariable("token") String token);
}
