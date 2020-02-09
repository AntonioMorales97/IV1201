package se.kth.iv1201.recruitmentbackend.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitmentbackend.application.exception.PersonNotFoundException;
import se.kth.iv1201.recruitmentbackend.domain.Application;
import se.kth.iv1201.recruitmentbackend.domain.Status;
import se.kth.iv1201.recruitmentbackend.presentation.dto.StatusDTO;
import se.kth.iv1201.recruitmentbackend.presentation.models.ApplicationListResponse;
import se.kth.iv1201.recruitmentbackend.repository.ApplicationRepository;
import se.kth.iv1201.recruitmentbackend.repository.StatusRepository;
/**
 * Class that handles all the service Logic regarding applications.
 *
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class ApplicationService {
	
	@Autowired 
	ApplicationRepository applicationRepo;
	@Autowired 
	StatusRepository statusRepo;
	/**
	 * Gets a specific Persons from database.
	 * @param id of person.
	 * @return ApplicationResponse of the person
	 */
	public Application findApplication(Long id) {
		Optional<Application> application = applicationRepo.findById(id);
	
		
		if(application.isEmpty()) {
			throw new PersonNotFoundException("Person could not be found by id "+id, 4);
		}
		
		return application.get();
	}
	/**
	 * Lists all Applications in the database.
	 * @return List containing all the applications.
	 */
	public List<ApplicationListResponse> findAllApplications() {
		List<ApplicationListResponse> applicationList = new ArrayList<ApplicationListResponse>();
		 List<Application> applications = applicationRepo.findAll();
		 applications.forEach(applicataion -> applicationList.add(createApplicationResponse(applicataion)) );
		 return applicationList;
		 
	}
	/**
	 * Updates a Application in the Database with new status.
	 * @param id of application
	 * @param statusDTO the new status.
	 * @return the updated Application
	 */
	public Application ChangeStatus(Long id, @Valid StatusDTO statusDTO) {
		Optional<Application> application = applicationRepo.findById(id);
		if(application.isEmpty()) {
			//THROW APPLICATAION NOT FOUND ERROR.
		}
		Optional<Status> status = statusRepo.findByName(statusDTO.getName());
		if(status.isEmpty()) {
			//THROW STATUS NOT FOUND ERROR.
		}
		application.get().setStatus(status.get());
		return application.get();
	}
private ApplicationListResponse createApplicationResponse(Application applicataion) {
		
		ApplicationListResponse application = new ApplicationListResponse(applicataion.getId(),applicataion.getPerson().getFirstName(), applicataion.getPerson().getLastName(),applicataion.getPerson().getEmail(),applicataion.getPerson().getSsn(),applicataion.getStatus(), applicataion.getCreateDate());
		return application;
	}


}
