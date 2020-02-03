package se.kth.iv1201.recruitmentbackend.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data

@Entity
public class CompetenceProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competence_profile_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToOne
    @JoinColumn(name = "competence_id")
    private Competence competence;

    @NotNull
    @Column(name = "years_of_experience")
    private int yearsOfExperience;
}
