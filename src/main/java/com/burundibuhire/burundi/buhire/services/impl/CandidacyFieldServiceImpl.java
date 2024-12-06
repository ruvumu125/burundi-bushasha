package com.burundibuhire.burundi.buhire.services.impl;

import com.burundibuhire.burundi.buhire.dto.CandidacyFieldDto;
import com.burundibuhire.burundi.buhire.repository.CandidacyFieldRepository;
import com.burundibuhire.burundi.buhire.services.CandidacyFieldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CandidacyFieldServiceImpl implements CandidacyFieldService {

    private final CandidacyFieldRepository candidacyFieldRepository;

    public CandidacyFieldServiceImpl(CandidacyFieldRepository candidacyFieldRepository) {
        this.candidacyFieldRepository = candidacyFieldRepository;
    }

    @Override
    public List<CandidacyFieldDto> findAll() {

        return candidacyFieldRepository.findAll().stream()
                .map(CandidacyFieldDto::fromEntity)
                .collect(Collectors.toList());
    }
}
