package com.burundibuhire.burundi.buhire.repository;

import com.burundibuhire.burundi.buhire.model.CandidateMember;
import com.burundibuhire.burundi.buhire.model.User;
import com.burundibuhire.burundi.buhire.model.VolunteerMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerMemberRepository extends JpaRepository<VolunteerMember,Long> {

    VolunteerMember findByUser(User user);
}
