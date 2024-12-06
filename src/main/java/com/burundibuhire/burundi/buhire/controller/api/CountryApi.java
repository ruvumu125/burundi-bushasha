package com.burundibuhire.burundi.buhire.controller.api;

import com.burundibuhire.burundi.buhire.dto.CountryDto;
import com.burundibuhire.burundi.buhire.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/country")
public interface CountryApi {

    @Operation(summary = "Récupérer la liste de tous les pays", description = "Cette methode permet de renvoyer la liste des pays qui existent" + "dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des pays / Une liste vide")
    })
    @GetMapping(value = Constants.APP_ROOT + "/countries/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CountryDto> findAll();
}
