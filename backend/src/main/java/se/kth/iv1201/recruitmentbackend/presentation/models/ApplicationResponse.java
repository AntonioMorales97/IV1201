package se.kth.iv1201.recruitmentbackend.presentation.models;

import java.util.List;
import org.springframework.hateoas.RepresentationModel;
import lombok.Data;


/**
 * Application response, the data returned for a application.
 */
@Data
public class ApplicationResponse extends RepresentationModel<ApplicationResponse>  {
	private Long id; 
	private String firstName;
	private String lastName;
	private String ssn;
	private String email;
	//private List experiance;
	//private List availablity;
	public ApplicationResponse(long id, String firstName, String lastName, String ssn,String email) {//List experiance,List availablity) {
		this.id = id;
		this.firstName= firstName;
		this.lastName = lastName;
		this.ssn= ssn;
		this.email=email;
		//this.experiance= experiance;
		//this.availablity=availablity;
	}
}
