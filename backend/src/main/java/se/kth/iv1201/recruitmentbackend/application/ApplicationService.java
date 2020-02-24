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
import se.kth.iv1201.recruitmentbackend.application.exception.OutdatedApplicationException;
import se.kth.iv1201.recruitmentbackend.application.exception.StatusNotFoundException;
import se.kth.iv1201.recruitmentbackend.domain.Application;
import se.kth.iv1201.recruitmentbackend.domain.Status;
import se.kth.iv1201.recruitmentbackend.presentation.dto.StatusDTO;
import se.kth.iv1201.recruitmentbackend.repository.ApplicationRepository;
import se.kth.iv1201.recruitmentbackend.repository.StatusRepository;

/**
 * Class that handles all the service logic regarding applications.
 *
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepo;

	@Autowired
	private StatusRepository statusRepo;
	
	private static final String APPLICATION_NOT_FOUND ="Application could not be found by id: ";
	private static final String STATUS_NOT_FOUND ="Status could not be found by name: ";
	private static final String OUTDATED_APPLICATION ="Could not save update, because current application verson is outdated.";
	

	/**
	 * Gets a specific <code>Application</code> from database.
	 * 
	 * @param id The ID of the <code>Application</code>.
	 * @return the <code>Application</code> if found.
	 * @throws ApplicationNotFoundException if no <code>Application</code> with the
	 *                                      given ID was found.
	 */
	public Application findApplication(Long id) {
		Optional<Application> application = applicationRepo.findById(id);

		if (application.isEmpty()) {
			throw new ApplicationNotFoundException(APPLICATION_NOT_FOUND + id);
		}

		return application.get();
	}

	/**
	 * Lists all <code>Application</code>s in the database.
	 * 
	 * @return all the <code>Application</code>s.
	 */
	public List<Application> findAllApplications() {
		List<Application> applications = applicationRepo.findAll();
		return applications;
	}

	/**
	 * Updates an <code>Application</code> in the database with new status.
	 * 
	 * @param id        The ID of the <code>Application</code>.
	 * @param statusDTO A <code>StatusDTO</code> containing the new status.
	 * @return the updated <code>Application</code>.
	 * @throws ApplicationNotFoundException if no <code>Application</code> was found
	 *                                      with the given ID.
	 * @throws StatusNotFoundException      if no <code>Status</code> was found in
	 *                                      the database that matches the given
	 *                                      status.
	 * @throws OutdatedApplicationException if the <code>Application</code> is
	 *                                      outdated, i.e. has an old version
	 *                                      compared to the <code>Application</code>
	 *                                      in the database.
	 * @throws CannotAcquireLockException   if trying to update an
	 *                                      <code>Application</code> at the same
	 *                                      time.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public Application changeStatus(Long id, @Valid StatusDTO statusDTO) {
		Optional<Application> application = applicationRepo.findById(id);
		if (application.isEmpty()) {
			throw new ApplicationNotFoundException(APPLICATION_NOT_FOUND + id);
		}
		Optional<Status> status = statusRepo.findByName(statusDTO.getName());
		if (status.isEmpty()) {
			throw new StatusNotFoundException(STATUS_NOT_FOUND + statusDTO.getName());
		}

		if (application.get().getVersion() != statusDTO.getVersion()) {
			throw new OutdatedApplicationException(
					OUTDATED_APPLICATION, application.get());

		}
		application.get().setStatus(status.get());
		return application.get();
	}

}
