package com.burundibuhire.burundi.buhire.dto;

import com.burundibuhire.burundi.buhire.model.CandidacyField;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidacyFieldDto {

    private Long id;
    private String candidacyFieldName;

    public static CandidacyFieldDto fromEntity(CandidacyField candidacyField) {
        if (candidacyField == null) {
            return null;
            // TODO: throw an exception
        }

        return CandidacyFieldDto.builder()
                .id(candidacyField.getId())
                .candidacyFieldName(candidacyField.getCandidacyFieldName())
                .build();
    }

    public static CandidacyField toEntity(CandidacyFieldDto candidacyFieldDto) {
        if (candidacyFieldDto == null) {
            return null;
            // TODO: throw an exception
        }

        CandidacyField candidacyField = new CandidacyField();
        candidacyField.setId(candidacyFieldDto.getId());
        candidacyField.setCandidacyFieldName(candidacyFieldDto.getCandidacyFieldName());

        return candidacyField;
    }
}
