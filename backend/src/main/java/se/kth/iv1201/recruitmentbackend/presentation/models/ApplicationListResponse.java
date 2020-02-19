package se.kth.iv1201.recruitmentbackend.presentation.models;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import se.kth.iv1201.recruitmentbackend.domain.Status;

/**
 * Represents a response in the form of a list of applications
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApplicationListResponse extends RepresentationModel<ApplicationListResponse> {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String ssn;
	private Status status;
	private Date creationDate;

	public ApplicationListResponse(Long id, String firstName, String lastName, String email, String ssn, Status status,
			Date creationDate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.ssn = ssn;
		this.status = status;
		this.creationDate = creationDate;
	}

}
