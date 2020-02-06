package se.kth.iv1201.recruitmentbackend.migration.dto;

import lombok.Data;

@Data
public class RoleDTO {
	private final String name;
	
	public RoleDTO(String name) {
		this.name = name;
	}
}
