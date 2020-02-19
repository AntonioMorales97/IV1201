package se.kth.iv1201.recruitmentbackend.presentation.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Represents a login attempt.
 *
 */
@Data
public class LoginForm {

	@NotNull(message = "Username missing")
	@NotBlank(message = "Username cannot be blank")
	private final String username;

	@NotNull(message = "Password missing")
	@NotBlank(message = "Password cannot be blank")
	private final String password;
}
