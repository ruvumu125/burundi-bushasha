package com.burundibuhire.burundi.buhire.controller;

import com.burundibuhire.burundi.buhire.controller.api.VolunteeringAreaApi;
import com.burundibuhire.burundi.buhire.dto.VolunteeringAreaDto;
import com.burundibuhire.burundi.buhire.services.VolunteeringAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VolunteeringAreaController implements VolunteeringAreaApi {

    private final VolunteeringAreaService volunteeringAreaService;

    @Autowired
    public VolunteeringAreaController(VolunteeringAreaService volunteeringAreaService) {
        this.volunteeringAreaService = volunteeringAreaService;
    }

    @Override
    public List<VolunteeringAreaDto> findAll() {
        return volunteeringAreaService.findAll();
    }
}
