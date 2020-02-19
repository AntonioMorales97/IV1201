package se.kth.iv1201.recruitmentbackend.presentation.models;

import lombok.Data;

/**
 * Response for a successful login attempt.
 * 
 * @author Gurk1
 *
 */
@Data
public class LoginResponse {
	private String jwtToken;
	private String role;

	public LoginResponse(String jwtToken, String role) {
		this.jwtToken = jwtToken;
		this.role = role;
	}
}
