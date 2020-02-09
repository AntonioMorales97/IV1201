package se.kth.iv1201.recruitmentbackend.presentation.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import se.kth.iv1201.recruitmentbackend.application.ApplicationService;
import se.kth.iv1201.recruitmentbackend.domain.Application;
import se.kth.iv1201.recruitmentbackend.presentation.dto.StatusDTO;
import se.kth.iv1201.recruitmentbackend.presentation.models.ApplicationListResponse;
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
	 * 
	 * @return a CollectionModel with all the applications embedded.
	 */
	@GetMapping("/applications")
	public CollectionModel<ApplicationListResponse> getAllApplications() {
		List<Application> applications = applicationService.findAllApplications();
		List<ApplicationListResponse> applicationList = new ArrayList<ApplicationListResponse>();
		applications.forEach(application -> {
			applicationList.add(createApplicationResponse(application));
			resourceAssembler.addLinksToApplicationResponse(application);
			
		});
		
		//applications
				//.forEach(applicationResponse -> resourceAssembler.addLinksToApplicationResponse(applicationResponse));
		return new CollectionModel<ApplicationListResponse>(applicationList,
				linkTo(methodOn(ApplicationController.class).getAllApplications()).withSelfRel());

	}

	/**
	 * Returns a specific application by id.
	 * 
	 * @param id of the application (Person.id)
	 * @return ApplicationResponse.
	 */
	@GetMapping("/application/{id}")
	public Application getApplication(@PathVariable Long id) {
		Application application = applicationService.findApplication(id);
		resourceAssembler.addLinksToApplicationResponse(application);
		return application;
	}

	@PutMapping("/alterstatus/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Application alterStatus(@RequestBody @Valid StatusDTO statusDTO, @PathVariable Long id) {
		Application application = applicationService.ChangeStatus(id, statusDTO);
		resourceAssembler.addLinksToApplicationResponse(application);
		return application;
	}
	private ApplicationListResponse createApplicationResponse(Application applicataion) {
		ApplicationListResponse application = new ApplicationListResponse(applicataion.getId(),
				applicataion.getPerson().getFirstName(), applicataion.getPerson().getLastName(),
				applicataion.getPerson().getSsn(), applicataion.getPerson().getEmail(), applicataion.getStatus(),
				applicataion.getCreateDate());
		return application;
	}
}
