package com.burundibuhire.burundi.buhire.repository;

import com.burundibuhire.burundi.buhire.model.CandidateMember;
import com.burundibuhire.burundi.buhire.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateMemberRepository extends JpaRepository<CandidateMember,Long> {

    CandidateMember findByUser(User user);
}
