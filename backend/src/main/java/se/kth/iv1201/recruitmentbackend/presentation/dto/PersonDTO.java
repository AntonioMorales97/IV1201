package se.kth.iv1201.recruitmentbackend.presentation.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;

import lombok.Data;
/**
 * A PersonDTO that holds all the information required to create a person.
 *
 */
@Data
public class PersonDTO {
	@Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "Only letters are allowed")
	@NotNull(message = "{personDto.firstName.missing}")
	@NotBlank(message = "{personDto.firstName.blank}")
	private String firstName;

	@Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "Only letters are allowed")
	@NotNull(message = "{personDto.lastName.missing}")
	@NotBlank(message = "{personDto.lastName.blank}")
	private String lastName;
	
	@NotBlank(message = "{personDto.email.blank}")
	@NotNull(message = "{personDto.email.missing}")
	@Email(message ="Email should be valid")
	private String email;
	
	@NotBlank(message = "{personDto.personalNumber.blank}")
	@NotNull(message = "{personDto.personalNumber.missing}")
	@Pattern(regexp="[\\d]{10}", message = "Personal number must be 10 digits")
	private String ssn;
	
	@NotNull(message = "{personDto.username.missing}")
	@NotBlank(message = "{personDto.username.blank}")
    private String username;
	
	@NotNull(message = "{personDto.password.missing}")
	@NotBlank(message = "{personDto.password.blank}")
	private String password;
	
	public PersonDTO(String firstName, String lastName, String email, String ssn, String username, String password) {
		this.firstName =firstName;
		this.lastName = lastName;
		this.email = email;
		this.ssn= ssn;
		this.username = username;
		this.password= password;
	}
}
