package com.burundibuhire.burundi.buhire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "political_histories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliticalHistory extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "candidate_member_id")
    private CandidateMember candidateMember;

    @Column(name = "politicalorganisation")
    private String politicalOrganisation;

    @Enumerated(EnumType.STRING)
    @Column(name = "politicalcategory")
    private PoliticalCategoryEnum politicalCategory;

    @Column(name = "function")
    private String function;

    @Column(name = "startdate")
    private LocalDate startDate;

    @Column(name = "enddate")
    private LocalDate endDate;
}
