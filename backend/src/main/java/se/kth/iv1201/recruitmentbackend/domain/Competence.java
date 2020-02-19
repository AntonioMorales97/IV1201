package se.kth.iv1201.recruitmentbackend.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Domain class representing an area of competence associated with a competence
 * profile.
 */
@Data
@Entity
public class Competence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "competence_id")
	private long id;

	@NotNull(message = "{competence.name.missing}")
	@NotBlank(message = "{competence.name.blank}")
	private String name;

	public Competence() {
	}

	public Competence(String name) {
		this.name = name;
	}
}
