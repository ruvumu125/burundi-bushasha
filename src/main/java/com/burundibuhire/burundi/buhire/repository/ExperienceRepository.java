package com.burundibuhire.burundi.buhire.repository;

import com.burundibuhire.burundi.buhire.model.CandidateMember;
import com.burundibuhire.burundi.buhire.model.Experience;
import com.burundibuhire.burundi.buhire.model.VolunteerMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findByVolunteerMember(VolunteerMember volunteerMember);
    List<Experience> findByCandidateMember(CandidateMember candidateMember);

}