package se.kth.iv1201.recruitmentbackend.migration.dto;

import lombok.Data;

/**
 * DTO representing a person in the old database
 */
@Data
public class PersonDTO {
	private final Long id;
	private final String name;
	private final String surname;
	private final String ssn;
	private final String email;
	private final String password;
	private final String username;
	private final String role;
	
	public PersonDTO(Long id, String name, String surname, String ssn, String email, String password, String username, String role) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.ssn = ssn;
		this.email = email;
		this.password = password;
		this.username = username;
		this.role = role;
	}
}
