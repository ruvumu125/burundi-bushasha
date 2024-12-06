package com.burundibuhire.burundi.buhire.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidacyfieldcandidatemember")
public class CandidacyFieldCandidateMember extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "candidate_member_id")
    private CandidateMember candidateMember;

    @ManyToOne
    @JoinColumn(name = "candidacy_field_id")
    private CandidacyField candidacyField;


}