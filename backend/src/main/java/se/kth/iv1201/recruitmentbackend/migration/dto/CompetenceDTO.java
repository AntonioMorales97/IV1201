package se.kth.iv1201.recruitmentbackend.migration.dto;

import lombok.Data;

/**
 * DTO representing a competence found in the old database.
 */
@Data
public class CompetenceDTO {
	private final String name;

	public CompetenceDTO(String name) {
		this.name = name;
	}
}
