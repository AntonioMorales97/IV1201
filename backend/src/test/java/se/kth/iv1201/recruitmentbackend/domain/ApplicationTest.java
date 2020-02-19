package se.kth.iv1201.recruitmentbackend.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import se.kth.iv1201.recruitmentbackend.repository.ApplicationRepository;
import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;
import se.kth.iv1201.recruitmentbackend.repository.RoleRepository;
import se.kth.iv1201.recruitmentbackend.repository.StatusRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	@Autowired
	private ApplicationRepository applicationRepo;

	@Autowired
	private StatusRepository statusRepo;

	@Autowired
	private PersonRepository personRepo;

	@Autowired
	private RoleRepository roleRepo;

	private Person dummyPerson1;
	private Person dummyPerson2;
	private Application application1;
	private Application application2;

	@Before
	public void setup() {
		applicationRepo.deleteAll();
		personRepo.deleteAll();
		roleRepo.deleteAll();
		Role r1 = new Role("recruit");
		Role r2 = new Role("applicant");
		roleRepo.save(r1);
		roleRepo.save(r2);
		dummyPerson1 = new Person("testyy", "testaryy", "applicationTest1@gmail.com", "9443898491", "applicationTest1",
				"då", roleRepo.findByName("applicant"));
		dummyPerson2 = new Person("Tests", "testsss", "applicationTest2@gmail.com", "938472819", "applicationTest2",
				"då", roleRepo.findByName("applicant"));
		personRepo.save(dummyPerson1);
		personRepo.save(dummyPerson2);

		application1 = new Application(statusRepo.findByName("unhandled").get(),
				personRepo.findByUsername("applicationTest1"));
		application2 = new Application(statusRepo.findByName("unhandled").get(),
				personRepo.findByUsername("applicationTest2"));
		applicationRepo.save(application1);
		applicationRepo.save(application2);
	}

	@Test
	public void applicationCreationTest() {
		List<Application> applications = applicationRepo.findAll();
		Application a1 = applications.get(0);
		Application a2 = applications.get(1);
		long id = a2.getId() - 1;
		assertEquals(a1.getId(), id);
		assertNotNull(a1.getCreateDate());
		assertEquals(a1.getStatus().getName(), "unhandled");
	}

	@After
	public void deStruct() {
		applicationRepo.deleteAll();
		personRepo.deleteAll();
		roleRepo.deleteAll();
	}
}
