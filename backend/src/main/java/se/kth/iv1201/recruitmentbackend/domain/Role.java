package se.kth.iv1201.recruitmentbackend.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Domain class representing the role of a person.
 */
@Data
@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private long id;

	@NotNull(message = "{role.name.missing}")
	@NotBlank(message = "{role.name.blank}")
	@Column(unique = true)
	private String name;

	/**
	 * Needed for JPA.
	 */
	public Role() {
	}

	/**
	 * Creates a <code>Role</code> with the given parameter.
	 * 
	 * @param name The name of the role.
	 */
	public Role(String name) {
		this.name = name;
	}
}
