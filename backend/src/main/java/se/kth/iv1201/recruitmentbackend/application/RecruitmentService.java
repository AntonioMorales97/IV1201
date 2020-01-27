package se.kth.iv1201.recruitmentbackend.application;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import se.kth.iv1201.recruitmentbackend.application.exception.IllegalTransactionException;
import se.kth.iv1201.recruitmentbackend.domain.Person;
import se.kth.iv1201.recruitmentbackend.presentation.dto.PersonDTO;
import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class RecruitmentService {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Person registerUser(PersonDTO personDTO) {
		
		if(usernameExists(personDTO.getUsername())) {
			throw new IllegalTransactionException("A person with the given username already exists!");
		}
		if(emailExists(personDTO.getEmail())) {
			throw new IllegalTransactionException("A person with the given email already exists!");
		}
		if(ssnExists(personDTO.getSsn())) {
			throw new IllegalTransactionException("A person with the given ssn already exists!");
		}
		Person newPerson= new Person(personDTO.getFirstName(), personDTO.getLastName(), personDTO.getEmail(), personDTO.getSsn(), 
				personDTO.getUsername(), passwordEncoder.encode(personDTO.getPassword()));
		this.personRepository.save(newPerson);
		
		return newPerson;
	}
	public Person findPerson(long id) {
		Optional<Person> person= personRepository.findById(id);
		if(person.isEmpty()) {
			throw new IllegalTransactionException("A person could not be found!");

		}
		return person.get();
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
