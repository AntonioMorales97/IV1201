package se.kth.iv1201.recruitmentbackend.presentation.models;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;
import lombok.Data;
import se.kth.iv1201.recruitmentbackend.domain.Availability;
import se.kth.iv1201.recruitmentbackend.domain.CompetenceProfile;


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
	private Set<CompetenceProfile> competenceProfile;
	private Set<Availability> availability;
	public ApplicationResponse(long id, String firstName, String lastName, String ssn,String email, 
			Set<CompetenceProfile> competenceProfile, Set<Availability> availability) {
		this.id = id;
		this.firstName= firstName;
		this.lastName = lastName;
		this.ssn= ssn;
		this.email=email;
		this.competenceProfile= competenceProfile.stream().collect(Collectors.toSet());
		this.availability =availability.stream().collect(Collectors.toSet());
	}	
}
