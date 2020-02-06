package se.kth.iv1201.recruitmentbackend.migration.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AvailabilityDTO {
	private final Date from;
	private final Date to;
	
	public AvailabilityDTO(Date from, Date to) {
		this.from = from;
		this.to = to;
	}
}
