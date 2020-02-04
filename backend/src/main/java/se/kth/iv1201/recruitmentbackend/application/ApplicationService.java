package se.kth.iv1201.recruitmentbackend.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import se.kth.iv1201.recruitmentbackend.application.exception.PersonNotFoundException;
import se.kth.iv1201.recruitmentbackend.domain.Person;
import se.kth.iv1201.recruitmentbackend.presentation.models.ApplicationResponse;
import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;
/**
 * Class that handles all the service Logic regarding applications.
 *
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class ApplicationService {
	@Autowired
	private PersonRepository personRepository;
	
	/**
	 * Gets a specific Persons from database.
	 * @param id of person.
	 * @return ApplicationResponse of the person
	 */
	public ApplicationResponse findApplication(Long id) {
		Optional<Person> person = personRepository.findById(id);
		if(person.isEmpty()) {
			throw new PersonNotFoundException("Person could not be found by id "+id, 4);
		}
		System.out.println(person.get());
		ApplicationResponse application =createApplicationResponse(person.get()) ;
		return application;
	}
	/**
	 * Lists all Applications in the database.
	 * @return List containing all the applications.
	 */
	public List<ApplicationResponse> findAllApplications() {
		
		List<Person> persons = personRepository.findAll();
		List<ApplicationResponse> applications = new ArrayList<ApplicationResponse>();
		persons.forEach(person -> applications.add(createApplicationResponse(person)) );
		return applications;
	}
	private ApplicationResponse createApplicationResponse(Person person) {
		
		ApplicationResponse application = new ApplicationResponse(person.getId(),person.getFirstName(), person.getLastName(),person.getSsn(),person.getEmail(), person.getCompetenceProfile(), person.getAvailability());
		return application;
	}

}
