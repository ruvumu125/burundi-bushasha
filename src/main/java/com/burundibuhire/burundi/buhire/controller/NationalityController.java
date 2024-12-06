package com.burundibuhire.burundi.buhire.controller;

import com.burundibuhire.burundi.buhire.controller.api.NationalityApi;
import com.burundibuhire.burundi.buhire.dto.NationalityDto;
import com.burundibuhire.burundi.buhire.services.NationalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NationalityController implements NationalityApi {

    private final NationalityService nationalityService;

    @Autowired
    public NationalityController(NationalityService nationalityService) {
        this.nationalityService = nationalityService;
    }

    @Override
    public List<NationalityDto> findAll() {
        return nationalityService.findAll();
    }
}
