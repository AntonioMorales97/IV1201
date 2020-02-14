package se.kth.iv1201.recruitmentbackend.presentation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * DTO class representing the status of an application.
 */
@Data
public class StatusDTO {
		
	@NotNull(message = "{status.name.missing}")
    @NotBlank(message = "{status.name.blank}")
    private String name;
	
	@NotNull(message = "{status.version.missing}")
    private long version;
	
	public StatusDTO() {}
	
    public StatusDTO(String name, long version) {
		this.name= name;
		this.version = version;
	}
}
