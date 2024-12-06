package com.burundibuhire.burundi.buhire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "experiences")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience extends AbstractEntity{

    @Column(name = "employername")
    private String employerName;

    @Enumerated(EnumType.STRING)
    @Column(name = "jobcategory")
    private JobCategoryEnum jobCategory;

    @Column(name = "jobtitle")
    private String jobTitle;

    @Column(name = "startdate")
    private LocalDate startDate;

    @Column(name = "enddate")
    private LocalDate  endDate;

    @ManyToOne
    @JoinColumn(name = "volunteer_member_id", nullable = true)
    private VolunteerMember volunteerMember;

    @ManyToOne
    @JoinColumn(name = "candidate_member_id", nullable = true)
    private CandidateMember candidateMember;
}