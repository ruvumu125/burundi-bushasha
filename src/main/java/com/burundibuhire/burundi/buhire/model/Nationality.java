package com.burundibuhire.burundi.buhire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "nationalities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nationality{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nationalityname")
    private String nationalityName;

    @OneToMany(mappedBy = "nationality")
    private List<NationalityMember> nationalityMemberList;
}
