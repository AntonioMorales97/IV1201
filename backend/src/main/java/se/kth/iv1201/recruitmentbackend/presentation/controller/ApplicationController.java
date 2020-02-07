package se.kth.iv1201.recruitmentbackend.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import se.kth.iv1201.recruitmentbackend.application.ApplicationService;
import se.kth.iv1201.recruitmentbackend.presentation.models.ApplicationResponse;
import se.kth.iv1201.recruitmentbackend.presentation.util.ResourceAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Validated
@CrossOrigin
public class ApplicationController {
	
	@Autowired
	ApplicationService applicationService;
	@Autowired
	ResourceAssembler resourceAssembler;
	/**
	 * Returns all Applications in the database.
	 * @return a CollectionModell with all the applications embedded.
	 */
	@GetMapping("/applications")
	public CollectionModel<ApplicationResponse> getAllApplications(){
		List<ApplicationResponse> applicationResponses = applicationService.findAllApplications();
		applicationResponses.forEach(applicationResponse->resourceAssembler.addLinksToApplicationResponse(applicationResponse));
		return new CollectionModel<ApplicationResponse> (applicationResponses, linkTo(methodOn(ApplicationController.class).getAllApplications()).withSelfRel());
		
	}
	/**
	 * Returns a specific application by id.
	 * @param id of the application (Person.id)
	 * @return ApplicationResponse.
	 */
	@GetMapping("/application/{id}")
	public ApplicationResponse getApplication(@PathVariable Long id) {
		ApplicationResponse applicationResponse =applicationService.findApplication(id);
		resourceAssembler.addLinksToApplicationResponse(applicationResponse);
		
		return applicationResponse;		
	}
}

