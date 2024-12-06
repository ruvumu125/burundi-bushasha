package com.burundibuhire.burundi.buhire.controller.api;

import com.burundibuhire.burundi.buhire.dto.NationalityDto;
import com.burundibuhire.burundi.buhire.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/nationality")
public interface NationalityApi {

    @Operation(summary = "Récupérer la liste de tous les nationalites", description = "Cette methode permet de renvoyer la liste des nationalites qui existent" + "dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des nationalites / Une liste vide")
    })
    @GetMapping(value = Constants.APP_ROOT + "/nationalities/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<NationalityDto> findAll();
}
