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
	

}
