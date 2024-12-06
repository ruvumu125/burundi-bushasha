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
@Table(name = "volunteering_areas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolunteeringArea extends AbstractEntity{

    @Column(name = "volunteeringareaname")
    private String volunteeringAreaName;

    @OneToMany(mappedBy = "volunteeringArea")
    private List<VolunteeringAreaVolunteerMember> volunteeringAreas;
}
