package com.burundibuhire.burundi.buhire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "volunteer_member")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerMember extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "volunteerMember")
    private List<VolunteeringAreaVolunteerMember> volunteeringAreaMembers;

    @OneToMany(mappedBy = "volunteerMember")
    private List<Degree> degrees;

    @OneToMany(mappedBy = "volunteerMember")
    private List<Experience> experience;


}
