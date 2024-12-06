package com.burundibuhire.burundi.buhire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "educations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Degree extends AbstractEntity{

    @Column(name = "school_name")
    private String schoolName;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_degree", length = 20)
    private DegreeEnum memberDegree;

    @Column(name = "field_of_study")
    private String fieldOfStudy;

    @Column(name = "startdate")
    private LocalDate startDate;

    @Column(name = "enddate")
    private LocalDate  endDate;



    @ManyToOne
    @JoinColumn(name = "volunteer_member_id", nullable = true)
    private VolunteerMember volunteerMember;


    @ManyToOne
    @JoinColumn(name = "candidate_member_user_id")
    private CandidateMember candidateMember;
}