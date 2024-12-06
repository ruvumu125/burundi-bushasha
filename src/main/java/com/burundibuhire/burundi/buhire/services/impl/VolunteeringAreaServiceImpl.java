package com.burundibuhire.burundi.buhire.services.impl;

import com.burundibuhire.burundi.buhire.dto.NationalityDto;
import com.burundibuhire.burundi.buhire.dto.VolunteeringAreaDto;
import com.burundibuhire.burundi.buhire.repository.VolunteeringAreaRepository;
import com.burundibuhire.burundi.buhire.services.VolunteeringAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VolunteeringAreaServiceImpl implements VolunteeringAreaService {

    private final VolunteeringAreaRepository volunteeringAreaRepository;

    public VolunteeringAreaServiceImpl(VolunteeringAreaRepository volunteeringAreaRepository) {
        this.volunteeringAreaRepository = volunteeringAreaRepository;
    }

    @Override
    public List<VolunteeringAreaDto> findAll() {

        return volunteeringAreaRepository.findAll().stream()
                .map(VolunteeringAreaDto::fromEntity)
                .collect(Collectors.toList());
    }
}
