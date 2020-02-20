package se.kth.iv1201.recruitmentbackend.presentation.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
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
import se.kth.iv1201.recruitmentbackend.application.exception.OutdatedApplicationException;
import se.kth.iv1201.recruitmentbackend.domain.Application;
import se.kth.iv1201.recruitmentbackend.presentation.dto.StatusDTO;
import se.kth.iv1201.recruitmentbackend.presentation.models.ApplicationMetadataResponse;
import se.kth.iv1201.recruitmentbackend.presentation.util.ResourceAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Controller class handling requests related to applications. This includes
 * listing applications and changing application status.
 */
@RestController
@Validated
@CrossOrigin
public class ApplicationController {

	@Autowired
	ApplicationService applicationService;

	@Autowired
	ResourceAssembler resourceAssembler;

	/**
	 * Returns all <code>Application</code>s in the database.
	 * 
	 * @return a <code>CollectionModel</code> with all the applications embedded.
	 */
	@GetMapping("/applications")
	public CollectionModel<ApplicationMetadataResponse> getAllApplications() {
		List<Application> applications = applicationService.findAllApplications();
		List<ApplicationMetadataResponse> applicationList = new ArrayList<ApplicationMetadataResponse>();
		applications.forEach(application -> {
			applicationList.add(createApplicationResponse(application));
			resourceAssembler.addLinksToApplication(application);

		});
		return new CollectionModel<ApplicationMetadataResponse>(applicationList,
				linkTo(methodOn(ApplicationController.class).getAllApplications()).withSelfRel());

	}

	/**
	 * Returns a specific <code>Application</code> by id.
	 * 
	 * @param id of the <code>application</code>.
	 * @return the <code>Application</code>.
	 */
	@GetMapping("/application/{id}")
	public Application getApplication(@PathVariable Long id) {
		Application application = applicationService.findApplication(id);
		resourceAssembler.addLinksToApplication(application);
		return application;
	}

	/**
	 * Changes the status of a <code>Application</code>.
	 *
	 * @param statusDTO DTO containing information about the new status.
	 * @param id        ID of the <code>Application</code> to update status.
	 * @return the altered <code>Application</code>.
	 */
	@PutMapping("/alter-status/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Application alterStatus(@RequestBody @Valid StatusDTO statusDTO, @PathVariable Long id) {
		try {
			Application application = applicationService.changeStatus(id, statusDTO);
			resourceAssembler.addLinksToApplication(application);
			return application;
		} catch (CannotAcquireLockException exc) {
			throw new OutdatedApplicationException(
					"Could not save update, because current application verson is outdated.",
					applicationService.findApplication(id));

		}
	}

	private ApplicationMetadataResponse createApplicationResponse(Application application) {
		ApplicationMetadataResponse applicationResponse = new ApplicationMetadataResponse(application.getId(),
				application.getPerson().getFirstName(), application.getPerson().getLastName(),
				application.getPerson().getEmail(), application.getPerson().getSsn(), application.getStatus(),
				application.getCreateDate());
		return applicationResponse;
	}
}
