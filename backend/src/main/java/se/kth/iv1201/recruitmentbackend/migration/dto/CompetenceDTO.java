package se.kth.iv1201.recruitmentbackend.migration.dto;

import lombok.Data;

@Data
public class CompetenceDTO {
	private final String name;
	
	public CompetenceDTO(String name) {
		this.name = name;
	}
}
