package com.burundibuhire.burundi.buhire.repository;

import com.burundibuhire.burundi.buhire.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    boolean existsByCountryName(String countryName);
}
