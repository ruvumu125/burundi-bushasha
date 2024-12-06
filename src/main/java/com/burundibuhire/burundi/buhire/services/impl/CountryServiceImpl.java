package com.burundibuhire.burundi.buhire.services.impl;

import com.burundibuhire.burundi.buhire.dto.CandidacyFieldDto;
import com.burundibuhire.burundi.buhire.dto.CountryDto;
import com.burundibuhire.burundi.buhire.repository.CountryRepository;
import com.burundibuhire.burundi.buhire.services.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<CountryDto> findAll() {

        return countryRepository.findAll().stream()
                .map(CountryDto::fromEntity)
                .collect(Collectors.toList());
    }
}
