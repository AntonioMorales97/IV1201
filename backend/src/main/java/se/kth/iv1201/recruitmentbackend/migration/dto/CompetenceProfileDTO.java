package se.kth.iv1201.recruitmentbackend.migration.dto;

import lombok.Data;

@Data
public class CompetenceProfileDTO {
	private final String name;
	private final double yearsOfExperience;
	
	public CompetenceProfileDTO(String name, double yearsOfExperience) {
		this.name = name;
		this.yearsOfExperience = yearsOfExperience;
	}
}
