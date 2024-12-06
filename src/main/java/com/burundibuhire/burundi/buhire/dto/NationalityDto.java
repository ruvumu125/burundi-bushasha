package com.burundibuhire.burundi.buhire.dto;

import com.burundibuhire.burundi.buhire.model.Nationality;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NationalityDto {

    private Long id;
    private String nationalityName;

    public static NationalityDto fromEntity(Nationality nationality) {
        if (nationality == null) {
            return null;
            // TODO: throw an exception
        }

        return NationalityDto.builder()
                .id(nationality.getId())
                .nationalityName(nationality.getNationalityName())
                .build();
    }

    public static Nationality toEntity(NationalityDto nationalityDto) {
        if (nationalityDto == null) {
            return null;
            // TODO: throw an exception
        }

        Nationality nationality = new Nationality();
        nationality.setId(nationalityDto.getId());
        nationality.setNationalityName(nationalityDto.getNationalityName());

        return nationality;
    }
}
