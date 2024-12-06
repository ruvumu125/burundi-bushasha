package com.burundibuhire.burundi.buhire.controller;

import com.burundibuhire.burundi.buhire.controller.api.CandidacyFieldApi;
import com.burundibuhire.burundi.buhire.dto.CandidacyFieldDto;
import com.burundibuhire.burundi.buhire.services.CandidacyFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CandidacyFieldController implements CandidacyFieldApi {

    private final CandidacyFieldService candidacyFieldService;

    @Autowired
    public CandidacyFieldController(CandidacyFieldService candidacyFieldService) {
        this.candidacyFieldService = candidacyFieldService;
    }

    @Override
    public List<CandidacyFieldDto> findAll() {
        return candidacyFieldService.findAll();
    }
}
