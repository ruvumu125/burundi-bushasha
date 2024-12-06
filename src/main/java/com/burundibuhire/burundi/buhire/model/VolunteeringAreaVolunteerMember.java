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
@Table(name = "volunteeringareamember")
public class VolunteeringAreaVolunteerMember extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "volunteer_member_id")
    private VolunteerMember volunteerMember;

    @ManyToOne
    @JoinColumn(name = "volunteering_area_member")
    private VolunteeringArea volunteeringArea;
}