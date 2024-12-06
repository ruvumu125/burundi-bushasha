package com.burundibuhire.burundi.buhire.dto;

import com.burundibuhire.burundi.buhire.model.Country;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryDto {

    private Long id;
    private String countryName;

    public static CountryDto fromEntity(Country country) {
        if( country== null) {
            return null;
        }

        return CountryDto.builder()
                .id(country.getId())
                .countryName(country.getCountryName())
                .build();
    }

    public static Country toEntity(CountryDto countryDto) {
        if(countryDto == null) {
            return null;
        }

        Country country = new Country();
        country.setId(countryDto.getId());
        country.setCountryName(countryDto.getCountryName());
        return country;
    }
}
