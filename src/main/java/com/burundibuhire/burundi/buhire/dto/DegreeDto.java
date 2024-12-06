package com.burundibuhire.burundi.buhire.dto;

import com.burundibuhire.burundi.buhire.model.Degree;
import com.burundibuhire.burundi.buhire.model.DegreeEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DegreeDto {

    private Long id;
    private String schoolName;
    private DegreeEnum memberDegree;
    private String fieldOfStudy;
    private LocalDate startDate;
    private LocalDate  endDate;

    public static DegreeDto fromEntity(Degree degree) {
        if (degree == null) {
            return null;
            // TODO: throw an exception
        }

        return DegreeDto.builder()
                .id(degree.getId())
                .schoolName(degree.getSchoolName())
                .memberDegree(degree.getMemberDegree())
                .fieldOfStudy(degree.getFieldOfStudy())
                .startDate(degree.getStartDate())
                .endDate(degree.getEndDate())
                .build();
    }

    public static Degree toEntity(DegreeDto degreeDto) {
        if (degreeDto == null) {
            return null;
            // TODO: throw an exception
        }

        Degree degree = new Degree();
        degree.setId(degreeDto.getId());
        degree.setSchoolName(degreeDto.getSchoolName());
        degree.setMemberDegree(degreeDto.getMemberDegree());
        degree.setFieldOfStudy(degreeDto.getFieldOfStudy());
        degree.setStartDate(degreeDto.getStartDate());
        degree.setEndDate(degreeDto.getEndDate());


        return degree;
    }
}
