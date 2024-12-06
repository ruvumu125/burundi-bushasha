package com.burundibuhire.burundi.buhire.dto;

import com.burundibuhire.burundi.buhire.model.Experience;
import com.burundibuhire.burundi.buhire.model.JobCategoryEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ExperienceDto {

    private Long id;
    private String employerName;
    private JobCategoryEnum jobCategory;
    private String jobTitle;
    private LocalDate startDate;
    private LocalDate  endDate;

    public static ExperienceDto fromEntity(Experience experience) {
        if (experience == null) {
            return null;
            // TODO: throw an exception
        }

        return ExperienceDto.builder()
                .id(experience.getId())
                .employerName(experience.getEmployerName())
                .jobCategory(experience.getJobCategory())
                .jobTitle(experience.getJobTitle())
                .startDate(experience.getStartDate())
                .endDate(experience.getEndDate())
                .build();
    }

    public static Experience toEntity(ExperienceDto experienceDto) {
        if (experienceDto == null) {
            return null;
            // TODO: throw an exception
        }

        Experience experience = new Experience();
        experience.setId(experienceDto.getId());
        experience.setEmployerName(experienceDto.getEmployerName());
        experience.setJobCategory(experienceDto.getJobCategory());
        experience.setJobTitle(experienceDto.getJobTitle());
        experience.setStartDate(experienceDto.getStartDate());
        experience.setEndDate(experienceDto.getEndDate());

        return experience;
    }
}

