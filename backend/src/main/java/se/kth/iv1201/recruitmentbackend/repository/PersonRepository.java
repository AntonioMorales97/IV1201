package se.kth.iv1201.recruitmentbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kth.iv1201.recruitmentbackend.domain.Person;


/**
 * JPA interface used to store and access person data in the database
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

	public Person findBySsn(String ssn);

	public Person findByUsername(String username);

	public Person findByEmail(String email);
}
