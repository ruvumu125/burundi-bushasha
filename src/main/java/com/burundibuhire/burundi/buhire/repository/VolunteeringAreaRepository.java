package com.burundibuhire.burundi.buhire.repository;

import com.burundibuhire.burundi.buhire.model.VolunteeringArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteeringAreaRepository extends JpaRepository<VolunteeringArea, Long> {
    VolunteeringArea findByVolunteeringAreaName(String volunteeringAreaName);
    boolean existsByVolunteeringAreaName(String volunteeringAreaName);
}
