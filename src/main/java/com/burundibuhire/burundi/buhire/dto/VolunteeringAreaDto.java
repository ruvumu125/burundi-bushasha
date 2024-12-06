package com.burundibuhire.burundi.buhire.dto;

import com.burundibuhire.burundi.buhire.model.VolunteeringArea;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VolunteeringAreaDto {

    private Long id;
    private String volunteeringAreaName;

    public static VolunteeringAreaDto fromEntity(VolunteeringArea volunteeringArea) {
        if (volunteeringArea == null) {
            return null;
            // TODO: throw an exception
        }

        return VolunteeringAreaDto.builder()
                .id(volunteeringArea.getId())
                .volunteeringAreaName(volunteeringArea.getVolunteeringAreaName())
                .build();
    }

    public static VolunteeringArea toEntity(VolunteeringAreaDto volunteeringAreaDto) {
        if (volunteeringAreaDto == null) {
            return null;
            // TODO: throw an exception
        }

        VolunteeringArea volunteeringArea = new VolunteeringArea();
        volunteeringArea.setId(volunteeringAreaDto.getId());
        volunteeringArea.setVolunteeringAreaName(volunteeringAreaDto.getVolunteeringAreaName());

        return volunteeringArea;
    }
}
