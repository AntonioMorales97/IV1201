package se.kth.iv1201.recruitmentbackend.presentation.models;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import se.kth.iv1201.recruitmentbackend.domain.Status;

@Data
public class ApplicationListResponse extends RepresentationModel<ApplicationListResponse>{
private Long id;
private String firstName;
private String lastName;
private String email;
private String ssn;
private Status status;

public ApplicationListResponse(Long id, String firstName, String lastName, String email, String ssn, Status status) {
	this.id = id;
	this.firstName=firstName;
	this.lastName=lastName;
	this.email=email;
	this.ssn=ssn;
	this.status=status;
}

}
