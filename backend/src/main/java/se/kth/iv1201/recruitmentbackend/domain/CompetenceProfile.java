package se.kth.iv1201.recruitmentbackend.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Domain class representing a competence profile for an application.
 */
@Data
@Entity
public class CompetenceProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "competence_profile_id")
	private long id;

	@ManyToOne
	@JoinColumn(name = "application_id")
	@ToString.Exclude
	@JsonIgnore
	private Application application;

	@OneToOne
	@JoinColumn(name = "competence_id")
	private Competence competence;

	@NotNull
	@Column(name = "years_of_experience")
	private double yearsOfExperience;

	/**
	 * Needed for JPA.
	 */
	public CompetenceProfile() {
	}

	/**
	 * Creates a <code>CompetenceProfile</code> with the given parameters.
	 * 
	 * @param application The <code>Application</code> of this competence profile.
	 * @param competence The <code>Competence</code> of this competence profile.
	 * @param yearsOfExperience The years of experience in this competence profile.
	 */
	public CompetenceProfile(Application application, Competence competence, double yearsOfExperience) {
		this.application = application;
		this.competence = competence;
		this.yearsOfExperience = yearsOfExperience;
	}
}
