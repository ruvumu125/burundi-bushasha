package com.burundibuhire.burundi.buhire.controller.api;

import com.burundibuhire.burundi.buhire.dto.VolunteeringAreaDto;
import org.springframework.web.bind.annotation.RequestMapping;
import com.burundibuhire.burundi.buhire.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequestMapping("/volunteering_area")
public interface VolunteeringAreaApi {

    @Operation(summary = "Récupérer la liste de tous les domaines de volontariat ", description = "Cette methode permet de renvoyer la liste des domaines de volontariat qui existent" + "dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des domaines de volontariat / Une liste vide")
    })
    @GetMapping(value = Constants.APP_ROOT + "/volunteering_areas/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<VolunteeringAreaDto> findAll();
}
