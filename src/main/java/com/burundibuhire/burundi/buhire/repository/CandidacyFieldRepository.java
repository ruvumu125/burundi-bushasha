package com.burundibuhire.burundi.buhire.repository;

import com.burundibuhire.burundi.buhire.model.CandidacyField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidacyFieldRepository extends JpaRepository<CandidacyField,Long> {

    boolean existsByCandidacyFieldName(String candidacyFieldName);
}
