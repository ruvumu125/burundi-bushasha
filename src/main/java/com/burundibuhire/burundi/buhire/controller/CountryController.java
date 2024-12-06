package com.burundibuhire.burundi.buhire.controller;

import com.burundibuhire.burundi.buhire.controller.api.CountryApi;
import com.burundibuhire.burundi.buhire.dto.CountryDto;
import com.burundibuhire.burundi.buhire.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController implements CountryApi {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<CountryDto> findAll() {
        return countryService.findAll();
    }
}
