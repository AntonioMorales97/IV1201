package se.kth.iv1201.recruitmentbackend.application;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.kth.iv1201.recruitmentbackend.application.exception.ApplicationNotFoundException;
import se.kth.iv1201.recruitmentbackend.application.exception.StatusNotFoundException;
import se.kth.iv1201.recruitmentbackend.domain.Application;
import se.kth.iv1201.recruitmentbackend.presentation.dto.StatusDTO;
import se.kth.iv1201.recruitmentbackend.repository.ApplicationRepository;
import se.kth.iv1201.recruitmentbackend.repository.StatusRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationServiceTest {

	@Autowired
	ApplicationRepository applicationRepo;
	
	@Autowired
	ApplicationService applicationService;
	@Autowired
	StatusRepository statusRepo;
	
	private Application dummyApplication;
	/**
	 * Sets up a dummy data for an Application
	 */
	@Before
	public void construct() {
		dummyApplication = new Application(statusRepo.findByName("unhandled").get());
		applicationRepo.save(dummyApplication);
	}
	
	@Test
	public void searchAllApplicationsTest() throws Exception{
		List<Application> applications =applicationService.findAllApplications();
		assertEquals(applications.get(0).getStatus().getName().toString(), "unhandled");
	}
	


	@Test
	public void searchOneApplicationTest() throws Exception{
		List<Application> applications =applicationService.findAllApplications();
		Application application = applicationService.findApplication(applications.get(0).getId());
		assertEquals(application.getStatus().getName().toString(), "unhandled");
		
	}
	@Test(expected= ApplicationNotFoundException.class)
	public void searchOneApplicationFailTest() throws Exception{
		 applicationService.findApplication((long) 1);
	}
	@Test
	public void changeStatus() {
		StatusDTO status = new StatusDTO("accepted");
		List<Application> applications =applicationService.findAllApplications();
		Application application = applicationService.changeStatus(applications.get(0).getId(), status);
		assertEquals(application.getStatus().getName().toString(), status.getName().toString());
	}
	@Test(expected=StatusNotFoundException.class)
	public void changeStatusFail() {
		List<Application> applications =applicationService.findAllApplications();
		StatusDTO status = new StatusDTO("bruno");
		applicationService.changeStatus(applications.get(0).getId(), status);
	}
	@After
	public void destruct() {
		applicationRepo.deleteAll();
	}
	

	/*
	 * @Autowired
RoleRepository roleRepo;
@Autowired
PersonRepository personRepo;
@Autowired
StatusRepository statusRepo;
@Autowired 
CompetenceRepository competenceRepo;
@Autowired
AvailabilityRepository availabilityRepo;
@Autowired
CompetenceProfileRepository compPRepo;
@Autowired
PasswordEncoder encoder;password =encoder.encode("123");
	r1 = new Role("recruit");
	r2= new Role("applicant");
	c1 = new Competence("Karuselldrift");
	c2 = new Competence("Korvgrillning");
	roleRepo.save(r1);
	roleRepo.save(r2);
	competenceRepo.save(c1);
	competenceRepo.save(c2);
	p1 = new Person("fa", "fa", "fa@gmail.com", "1948281092","fa", password, r1);
	personRepo.save(p1);
	a1 = new Application(statusRepo.findByName("unhandled").get(),p1);
	applicationRepo.save(a1);
	cp2= new CompetenceProfile(a1,c2, 2.0);
	compPRepo.save(cp2);
	av1 = new Availability(a1, new Date(2014/02/23),new Date(2014/05/25)); 
	availabilityRepo.save(av1);
	a1.getAvailability().add(av1);
	a1.getCompetenceProfile().add(cp2);*/
}
