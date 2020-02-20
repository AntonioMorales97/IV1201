package se.kth.iv1201.recruitmentbackend.presentation.models;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import se.kth.iv1201.recruitmentbackend.domain.Status;

/**
 * Represents one shorter response of an <code>Application</code> to be presented. 
 * With other words: Represents the metadata of an <code>Application</code>.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApplicationMetadataResponse extends RepresentationModel<ApplicationMetadataResponse> {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String ssn;
	private Status status;
	private Date creationDate;

	/**
	 * Creates an instance of this class with the given parameters.
	 * 
	 * @param id The ID of the <code>Application</code>.
	 * @param firstName The first name of the person owning the <code>Application</code>.
	 * @param lastName The last name of the person owning the <code>Application</code>.
	 * @param email The email of the person owning the <code>Application</code>.
	 * @param ssn The SSN of the person owning the <code>Application</code>.
	 * @param status The status of the <code>Application</code>.
	 * @param creationDate The creation date of the <code>Application</code>.
	 */
	public ApplicationMetadataResponse(Long id, String firstName, String lastName, String email, String ssn, Status status,
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
