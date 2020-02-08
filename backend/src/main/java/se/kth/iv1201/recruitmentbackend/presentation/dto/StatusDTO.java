package se.kth.iv1201.recruitmentbackend.presentation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class StatusDTO {
		
	@NotNull(message = "{status.name.missing}")
    @NotBlank(message = "{status.name.blank}")
   
    private String name;
	
	public StatusDTO() {}
    public StatusDTO(String name) {
		this.name= name;
	}
}
