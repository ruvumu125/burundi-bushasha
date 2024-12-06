package com.burundibuhire.burundi.buhire.repository;

import com.burundibuhire.burundi.buhire.model.CandidateMember;
import com.burundibuhire.burundi.buhire.model.SupportingMember;
import com.burundibuhire.burundi.buhire.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportingMemberRepository extends JpaRepository<SupportingMember,Long> {

    SupportingMember findByUser(User user);
}
