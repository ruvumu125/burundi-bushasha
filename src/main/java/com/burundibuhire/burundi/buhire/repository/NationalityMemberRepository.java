package com.burundibuhire.burundi.buhire.repository;

import com.burundibuhire.burundi.buhire.model.Nationality;
import com.burundibuhire.burundi.buhire.model.NationalityMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalityMemberRepository extends JpaRepository<NationalityMember, Long> {

}
