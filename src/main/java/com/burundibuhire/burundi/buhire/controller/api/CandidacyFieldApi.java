package com.burundibuhire.burundi.buhire.controller.api;

import com.burundibuhire.burundi.buhire.dto.CandidacyFieldDto;
import com.burundibuhire.burundi.buhire.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/candidacy_field")
public interface CandidacyFieldApi {

    @Operation(summary = "Récupérer la liste de tous les domaines de candidature", description = "Cette methode permet de renvoyer la liste des domaines de candidature qui existent" + "dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des types vehicules / Une liste vide")
    })
    @GetMapping(value = Constants.APP_ROOT + "/candidacy_fields/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CandidacyFieldDto> findAll();
}
