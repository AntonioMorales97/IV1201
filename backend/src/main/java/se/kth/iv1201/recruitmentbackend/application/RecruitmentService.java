package se.kth.iv1201.recruitmentbackend.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import se.kth.iv1201.recruitmentbackend.application.exception.IllegalTransactionException;
import se.kth.iv1201.recruitmentbackend.domain.Person;
import se.kth.iv1201.recruitmentbackend.presentation.dto.PersonDTO;
import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;
import se.kth.iv1201.recruitmentbackend.repository.RoleRepository;


/**
 * Handles all the logic for registration of new Persons.
 *
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class RecruitmentService {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepo;
	
	/**
	 * Adds a specific Person to the database.
	 * @param personDTO The data that represents the user to add to the database.
	 * @return the registered person. // COULD BE VOID ASWELL.
	 */
	public void registerUser(PersonDTO personDTO) {
		
		if(usernameExists(personDTO.getUsername())) {
			throw new IllegalTransactionException("A person with the given username already exists!",1);
		}
		if(emailExists(personDTO.getEmail())) {
			
			throw new IllegalTransactionException("A person with the given email already exists!",2 );
		}
		if(ssnExists(personDTO.getSsn())) {
			
			throw new IllegalTransactionException("A person with the given ssn already exists!" ,3);
		}
		Person newPerson= new Person(personDTO.getFirstName(), personDTO.getLastName(), personDTO.getEmail(), personDTO.getSsn(), 
				personDTO.getUsername(), passwordEncoder.encode(personDTO.getPassword()),roleRepo.findByName("applicant"));
		this.personRepository.save(newPerson);  
		
		return;
	}
	
	private boolean ssnExists(String ssn) {
		return this.personRepository.findBySsn(ssn) !=null?true:false;
		}
	private boolean usernameExists(String username) {
		return this.personRepository.findByUsername(username) !=null?true:false;
	}
	private boolean emailExists(String email) {
		return this.personRepository.findByEmail(email) !=null?true:false;
	}

}
