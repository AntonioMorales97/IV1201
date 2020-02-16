package se.kth.iv1201.recruitmentbackend.application;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import se.kth.iv1201.recruitmentbackend.application.exception.ApplicationNotFoundException;
import se.kth.iv1201.recruitmentbackend.application.exception.StatusNotFoundException;
import se.kth.iv1201.recruitmentbackend.domain.Application;
import se.kth.iv1201.recruitmentbackend.domain.Status;
import se.kth.iv1201.recruitmentbackend.presentation.dto.StatusDTO;
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
	 * 
	 * @param id of person.
	 * @return ApplicationResponse of the person
	 */
	public Application findApplication(Long id) {
		Optional<Application> application = applicationRepo.findById(id);

		if (application.isEmpty()) {
			throw new ApplicationNotFoundException("Application could not be found by id: "+id);
		}

		return application.get();
	}

	/**
	 * Lists all Applications in the database.
	 * 
	 * @return List containing all the applications.
	 */
	public List<Application> findAllApplications() {
		
		List<Application> applications = applicationRepo.findAll();
		
		return applications;

	}

	/**
	 * Updates a Application in the Database with new status.
	 * 
	 * @param id        of application
	 * @param statusDTO the new status.
	 * @return the updated Application
	 */
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public Application changeStatus(Long id, @Valid StatusDTO statusDTO) {
		Optional<Application> application = applicationRepo.findById(id);
		if (application.isEmpty()) {
			throw new ApplicationNotFoundException("Application could not be found by id: "+id);
		}
		Optional<Status> status = statusRepo.findByName(statusDTO.getName());
		if (status.isEmpty()) {
			throw new StatusNotFoundException("Status could not be found by name: "+statusDTO.getName()); 
		}
		
		if(application.get().getVersion() != statusDTO.getVersion()) {
			System.out.println("ERROR " + "new vers: " + application.get().getVersion() + " old vers: " + statusDTO.getVersion());
			throw new ApplicationNotFoundException("ERROR" + "new vers: " + application.get().getVersion() + " old vers: " + statusDTO.getVersion());
		}
		System.out.println("SLeeping");
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("wakeup");
		application.get().setStatus(status.get());
		return application.get();
	}

	

}
