package com.burundibuhire.burundi.buhire.dto;

import com.burundibuhire.burundi.buhire.model.PoliticalCategoryEnum;
import com.burundibuhire.burundi.buhire.model.PoliticalHistory;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PoliticalHistoryDto {

    private Long id;
    private String politicalOrganisation;
    private PoliticalCategoryEnum politicalCategory;
    private String function;
    private LocalDate startDate;
    private LocalDate  endDate;

    public static PoliticalHistoryDto fromEntity(PoliticalHistory politicalHistory) {
        if (politicalHistory == null) {
            return null;
            // TODO: throw an exception
        }

        return PoliticalHistoryDto.builder()
                .id(politicalHistory.getId())
                .politicalOrganisation(politicalHistory.getPoliticalOrganisation())
                .politicalCategory(politicalHistory.getPoliticalCategory())
                .function(politicalHistory.getFunction())
                .startDate(politicalHistory.getStartDate())
                .endDate(politicalHistory.getEndDate())
                .build();
    }

    public static PoliticalHistory toEntity(PoliticalHistoryDto politicalHistoryDto) {
        if (politicalHistoryDto == null) {
            return null;
            // TODO: throw an exception
        }

        PoliticalHistory politicalHistory = new PoliticalHistory();
        politicalHistory.setId(politicalHistoryDto.getId());
        politicalHistory.setPoliticalOrganisation(politicalHistoryDto.getPoliticalOrganisation());
        politicalHistory.setPoliticalCategory(politicalHistoryDto.getPoliticalCategory());
        politicalHistory.setFunction(politicalHistoryDto.getFunction());
        politicalHistory.setStartDate(politicalHistoryDto.getStartDate());
        politicalHistory.setEndDate(politicalHistoryDto.getEndDate());

        return politicalHistory;
    }
}
