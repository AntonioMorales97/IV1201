package se.kth.iv1201.recruitmentbackend.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

/**
 * Domain class representing a person (user) in the recruitment system.
 */
@Data
@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private long id;

	@Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "Only letters are allowed")
	@NotNull(message = "{person.firstName.missing}")
	@NotBlank(message = "{person.firstName.blank}")
	private String firstName;

	@Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "Only letters are allowed")
	@NotNull(message = "{person.lastName.missing}")
	@NotBlank(message = "{person.lastName.blank}")
	private String lastName;

	@Email(message = "Email should be valid")
	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String ssn;

	@NotNull(message = "{person.username.missing}")
	@NotBlank(message = "{person.username.blank}")
	@Column(unique = true)
	private String username;

	@NotNull(message = "{person.password.missing}")
	@NotBlank(message = "{person.password.blank}")
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "role_id")
	private Role role;

	/**
	 * Needed for JPA.
	 */
	public Person() {
	};

	/**
	 * Creates a new person with the specified parameters.
	 *
	 * @param name     The user's name
	 * @param surname  The user's surname
	 * @param email    The user's email
	 * @param ssn      The user's social security number
	 * @param username The user's username
	 * @param password The user's password
	 */
	public Person(String name, String surname, String email, String ssn, String username, String password, Role role) {
		this.firstName = name;
		this.lastName = surname;
		this.email = email;
		this.ssn = ssn;
		this.username = username;
		this.password = password;
		this.role = role;
	}
}
