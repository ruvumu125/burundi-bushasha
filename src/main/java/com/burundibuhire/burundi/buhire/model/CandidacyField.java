package com.burundibuhire.burundi.buhire.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "candidacy_field")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidacyField extends AbstractEntity {

    @Column(name = "candidacyname")
    private String candidacyFieldName;

    @OneToMany(mappedBy = "candidacyField") // Correction ici
    private List<CandidacyFieldCandidateMember> candidacyFieldCandidateMembers;

}
