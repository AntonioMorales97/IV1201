package se.kth.iv1201.recruitmentbackend.presentation.models;

import lombok.Data;

@Data
public class LoginResponse {
	private String jwtToken;
	
	public LoginResponse(String jwtToken) {
		this.jwtToken= jwtToken;
	}
}
