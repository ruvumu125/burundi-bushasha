package com.burundibuhire.burundi.buhire.repository;

import com.burundibuhire.burundi.buhire.model.CandidateMember;
import com.burundibuhire.burundi.buhire.model.PoliticalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoliticalHistoryRepository extends JpaRepository<PoliticalHistory, Long> {
    List<PoliticalHistory> findByCandidateMember(CandidateMember candidateMember);
}
