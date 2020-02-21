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
/**
 * Tests for ApplicationService class.
 */
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
	/**
	 * test the findAllApplications function.
	 * Expect a result within a list, checks status to verify that the application has a status.
	 * @throws Exception in case an error occurs 
	 */
	@Test
	public void searchAllApplicationsTest() throws Exception {
		List<Application> applications = applicationService.findAllApplications();
		assertEquals(applications.get(0).getStatus().getName().toString(), "unhandled");
	}
	/**
	 * Test the findApplication
	 * Verify that the status of the first application is equal to the first application in the database.
	 * @throws Exception in case an error occurs 
	 */
	@Test
	public void searchOneApplicationTest() throws Exception {
		List<Application> applications = applicationService.findAllApplications();
		Application application = applicationService.findApplication(applications.get(0).getId());
		assertEquals(application.getStatus().getName().toString(), "unhandled");

	}
	/**
	 * Tests findApplication function in cases it should fail, like a application id that does not exist.
	 * @expected ApplicationNotFoundException. 
	 * @throws Exception in case an error occurs 
	 */
	@Test(expected = ApplicationNotFoundException.class)
	public void searchOneApplicationFailTest() throws Exception {
		applicationService.findApplication((long) 10000);
	}
	/**
	 * Test changeStatus function and veritifies that the status is altered after it has executed.
	 * @throws Exception in case an error occurs 
	 */
	@Test
	public void changeStatus() throws Exception{
		List<Application> applications = applicationService.findAllApplications();
		StatusDTO status = new StatusDTO("accepted", applications.get(0).getVersion());
		Application application = applicationService.changeStatus(applications.get(0).getId(), status);
		assertEquals(application.getStatus().getName().toString(), status.getName().toString());
	}
	/**
	 * Test changeStatus function, in a case where it should fail, status name "bruno" 
	 *  which should fail cause bruno is not a status.
	 * @throws Exception in case an error occurs 
	 */
	@Test(expected = StatusNotFoundException.class)
	public void changeStatusFail() throws Exception{
		List<Application> applications = applicationService.findAllApplications();
		StatusDTO status = new StatusDTO("bruno", applications.get(0).getVersion());
		applicationService.changeStatus(applications.get(0).getId(), status);
	}
	
	/**
	 * Test changeStatus functions race condition, it should fail the second request with exception 
	 * CannotAcquireLockException.
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	//@Test(expected = CannotAcquireLockException.class)
	public void simultaniousUpdateTest() throws Exception {
		List<Application> user1 = applicationService.findAllApplications();
		List<Application> user2 = applicationService.findAllApplications();
		StatusDTO status1 = new StatusDTO("accepted", user1.get(0).getVersion());
		StatusDTO status2 = new StatusDTO("rejected", user2.get(0).getVersion());
		
		new Thread(() -> {
			Application application1 = applicationService.changeStatus(user1.get(0).getId(), status1);
		}).start();
		Application application2 = applicationService.changeStatus(user2.get(0).getId(), status2);
	}
	
	/**
	 * Clean up
	 */
	@After
	public void destruct() {
		applicationRepo.deleteAll();
	}

}
