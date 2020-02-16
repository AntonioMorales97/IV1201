package se.kth.iv1201.recruitmentbackend.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import se.kth.iv1201.recruitmentbackend.repository.ApplicationRepository;
import se.kth.iv1201.recruitmentbackend.repository.AvailabilityRepository;
import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;
import se.kth.iv1201.recruitmentbackend.repository.RoleRepository;
import se.kth.iv1201.recruitmentbackend.repository.StatusRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvailabilityTest {
	@Autowired
	private ApplicationRepository applicationRepo;
	@Autowired
	private StatusRepository statusRepo;
	@Autowired
	private PersonRepository personRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	AvailabilityRepository availabilityRepo;
	private Person dummyPerson1;
	private Person dummyPerson2;
	private Application application1;
	private Application application2;
	private Availability availability;
	@Before
	public void setup() {
		applicationRepo.deleteAll();
		personRepo.deleteAll();
		roleRepo.deleteAll();
		availabilityRepo.deleteAll();
		Role r1 = new Role("recruit");
		Role r2 = new Role("applicant");
		roleRepo.save(r1);
		roleRepo.save(r2);
		dummyPerson1 = new Person("testyy","testaryy","applicationTest1@gmail.com","9443898491","applicationTest1","då", roleRepo.findByName("applicant"));
		dummyPerson2 = new Person("Tests", "testsss", "applicationTest2@gmail.com", "938472819", "applicationTest2","då", roleRepo.findByName("applicant"));
		personRepo.save(dummyPerson1);
		personRepo.save(dummyPerson2);
		
		application1 = new Application(statusRepo.findByName("unhandled").get(), personRepo.findByUsername("applicationTest1"));
		application2 = new Application(statusRepo.findByName("unhandled").get(), personRepo.findByUsername("applicationTest2"));
		applicationRepo.save(application1);
		applicationRepo.save(application2);
		availability = new Availability(applicationRepo.findAll().get(0),new Date(2014/02/23),new Date(2014/05/25));
		application1.getAvailability().add(availability);
		availabilityRepo.save(availability);
	}
	
	@Test
	public void applicationCreationTest() {
		List<Availability> avList = availabilityRepo.findAll();
		Availability av1 = avList.get(0);
		System.out.println(av1.getApplication());
	}
	@After
	public void destruct() {
		applicationRepo.deleteAll();
		personRepo.deleteAll();
		roleRepo.deleteAll();
		availabilityRepo.deleteAll();
	}
}
