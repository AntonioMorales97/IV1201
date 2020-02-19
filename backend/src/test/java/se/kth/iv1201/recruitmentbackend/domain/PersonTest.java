package se.kth.iv1201.recruitmentbackend.domain;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;
import se.kth.iv1201.recruitmentbackend.repository.RoleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonTest {

	@Autowired
	private PersonRepository personRepo;

	@Autowired
	private RoleRepository roleRepo;

	private Person dummyPerson1;
	private Person dummyPerson2;

	@Before
	public void setup() {
		personRepo.deleteAll();
		roleRepo.deleteAll();
		Role r1 = new Role("recruit");
		Role r2 = new Role("applicant");
		roleRepo.save(r1);
		roleRepo.save(r2);
		dummyPerson1 = new Person("testyy", "testaryy", "applicationTest1@gmail.com", "9443898491", "applicationTest1",
				"då", roleRepo.findByName("applicant"));
		dummyPerson2 = new Person("Tests", "testsss", "applicationTest2@gmail.com", "938472819", "applicationTest2",
				"då", roleRepo.findByName("recruit"));
		personRepo.save(dummyPerson1);
		personRepo.save(dummyPerson2);

	}

	@Test
	public void personCreatedTest() {
		List<Person> persons = personRepo.findAll();
		Person p1 = persons.get(0);
		Person p2 = persons.get(1);
		long id = p2.getId() - 1;
		assertEquals(p1.getId(), id);
		assertEquals(p1.getRole().getName(), "applicant");
		assertEquals(p2.getRole().getName(), "recruit");
	}

	@Test(expected = ConstraintViolationException.class)
	public void invalidFirstNameTest() {
		saveInvalidPerson(null, "testaryy", "applicationTest1@gmail.com", "9443898491", "applicationTest1", "då",
				roleRepo.findByName("applicant"));

	}

	@Test(expected = DataIntegrityViolationException.class)
	public void invalidLastNameTest() {
		saveInvalidPerson("testy", "null", "applicationTest1@gmail.com", "9443898491", "applicationTest1", "då",
				roleRepo.findByName("applicant"));
	}

	@Test(expected = ConstraintViolationException.class)
	public void invalidLastNameblankTest() {
		saveInvalidPerson("testy", "", "applicationTest1@gmail.com", "9443898491", "applicationTest1", "då",
				roleRepo.findByName("applicant"));
	}

	private void saveInvalidPerson(String firstName, String lastName, String email, String ssn, String username,
			String password, Role role) {
		Person person = new Person(firstName, lastName, email, ssn, username, password, role);
		personRepo.save(person);
	}

}
