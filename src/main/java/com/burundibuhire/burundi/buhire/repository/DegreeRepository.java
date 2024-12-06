package com.burundibuhire.burundi.buhire.repository;

import com.burundibuhire.burundi.buhire.model.CandidateMember;
import com.burundibuhire.burundi.buhire.model.Degree;
import com.burundibuhire.burundi.buhire.model.VolunteerMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {
    List<Degree> findByVolunteerMember(VolunteerMember volunteerMember);
    List<Degree> findByCandidateMember(CandidateMember candidateMember);
}


