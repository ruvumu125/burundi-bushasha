package com.burundibuhire.burundi.buhire.services.impl;

import com.burundibuhire.burundi.buhire.dto.CountryDto;
import com.burundibuhire.burundi.buhire.dto.NationalityDto;
import com.burundibuhire.burundi.buhire.repository.NationalityRepository;
import com.burundibuhire.burundi.buhire.services.NationalityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NationalityServiceImpl implements NationalityService {

    private final NationalityRepository nationalityRepository;

    public NationalityServiceImpl(NationalityRepository nationalityRepository) {
        this.nationalityRepository = nationalityRepository;
    }

    @Override
    public List<NationalityDto> findAll() {

        return nationalityRepository.findAll().stream()
                .map(NationalityDto::fromEntity)
                .collect(Collectors.toList());
    }
}
