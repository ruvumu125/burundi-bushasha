package com.burundibuhire.burundi.buhire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "supporting_member")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupportingMember extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}