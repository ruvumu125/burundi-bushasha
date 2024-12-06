package com.burundibuhire.burundi.buhire.repository;

import com.burundibuhire.burundi.buhire.model.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalityRepository extends JpaRepository<Nationality, Long> {
    Nationality findByNationalityName(String nationalityName);
    boolean existsByNationalityName(String nationalityName);

}

