package com.burundibuhire.burundi.buhire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "candidate_member")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateMember extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "candidateMember") // Relation avec CandidacyFieldCandidateMember
    private List<CandidacyFieldCandidateMember> candidacyFieldCandidateMembers;

    // Ethnicity is applicable only for CandidateMember
    @Enumerated(EnumType.STRING)
    @Column(name = "ethnicity", nullable = true) // Nullable for non-candidate members
    private EthnicityEnum ethnicity;

    @OneToMany(mappedBy = "candidateMember")
    private List<Degree> degrees;

    @OneToMany(mappedBy = "candidateMember")
    private List<Experience> experience;

    @OneToMany(mappedBy = "candidateMember")
    private List<PoliticalHistory> politicalHistories;


    @Column(name = "certificate_of_residence", columnDefinition = "bytea", nullable = true)
    private byte[] certificateOfResidence;


    @Column(name = "certificate_of_registration", columnDefinition = "bytea", nullable = true)
    private byte[] certificateOfRegistration;


}
