package se.kth.iv1201.recruitmentbackend.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class CompetenceProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competence_profile_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @ToString.Exclude
    @JsonIgnore
    private Person person;

    @OneToOne
    @JoinColumn(name = "competence_id")
    private Competence competence;

    @NotNull
    @Column(name = "years_of_experience")
    private double yearsOfExperience;
    
    public CompetenceProfile() {}
    
    public CompetenceProfile(Person person, Competence competence, double yearsOfExperience) {
    	this.person=person;
    	this.competence=competence;
    	this.yearsOfExperience=yearsOfExperience;
    }
}
