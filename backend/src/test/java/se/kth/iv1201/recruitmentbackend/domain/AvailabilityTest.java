package se.kth.iv1201.recruitmentbackend.domain;

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

import se.kth.iv1201.recruitmentbackend.enums.ApplicationStatus;
import se.kth.iv1201.recruitmentbackend.enums.RoleNames;
import se.kth.iv1201.recruitmentbackend.repository.ApplicationRepository;
import se.kth.iv1201.recruitmentbackend.repository.AvailabilityRepository;
import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;
import se.kth.iv1201.recruitmentbackend.repository.RoleRepository;
import se.kth.iv1201.recruitmentbackend.repository.StatusRepository;
/**
 * Test the Availability domain class.
 */
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
	private Date fromDate = new Date();
	/**
	 * Dummy data setup.
	 */
	@Before
	public void setup() {
		applicationRepo.deleteAll();
		personRepo.deleteAll();
		roleRepo.deleteAll();
		availabilityRepo.deleteAll();
		Role r1 = new Role(RoleNames.recruit.toString());
		Role r2 = new Role(RoleNames.applicant.toString());
		roleRepo.save(r1);
		roleRepo.save(r2);
		dummyPerson1 = new Person("testyy", "testaryy", "applicationTest1@gmail.com", "9443898491", "applicationTest1",
				"då", roleRepo.findByName(RoleNames.applicant.toString()));
		dummyPerson2 = new Person("Tests", "testsss", "applicationTest2@gmail.com", "938472819", "applicationTest2",
				"då", roleRepo.findByName(RoleNames.applicant.toString()));
		personRepo.save(dummyPerson1);
		personRepo.save(dummyPerson2);

		application1 = new Application(statusRepo.findByName(ApplicationStatus.unhandled.toString()).get(),
				personRepo.findByUsername("applicationTest1"));
		application2 = new Application(statusRepo.findByName(ApplicationStatus.unhandled.toString()).get(),
				personRepo.findByUsername("applicationTest2"));
		applicationRepo.save(application1);
		applicationRepo.save(application2);
		availability = new Availability(applicationRepo.findAll().get(0), fromDate, new Date());
		application1.getAvailability().add(availability);
		availabilityRepo.save(availability);
	}
	/**
	 * Test that the availability dummy data is inside the database, thus that the domain model works.
	 */
	@Test
	public void availabilityCreateTest() {
		List<Availability> avList = availabilityRepo.findAll();
		Availability av1 = avList.get(0);
		System.out.println(av1.toString());
		assertNotNull(av1.getFromDate().toString());
		assertNotNull(av1.getToDate().toString());
	}
	/**
	 * DeStruct function, removes all dummydata.
	 */
	@After
	public void destruct() {
		applicationRepo.deleteAll();
		personRepo.deleteAll();
		roleRepo.deleteAll();
		availabilityRepo.deleteAll();
	}
}
