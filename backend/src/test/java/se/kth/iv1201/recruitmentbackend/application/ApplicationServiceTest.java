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
import se.kth.iv1201.recruitmentbackend.application.exception.OutdatedApplicationException;
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
	public void searchAllApplicationsTest() throws Exception {
		List<Application> applications = applicationService.findAllApplications();
		assertEquals(applications.get(0).getStatus().getName().toString(), "unhandled");
	}

	@Test
	public void searchOneApplicationTest() throws Exception {
		List<Application> applications = applicationService.findAllApplications();
		Application application = applicationService.findApplication(applications.get(0).getId());
		assertEquals(application.getStatus().getName().toString(), "unhandled");

	}

	@Test(expected = ApplicationNotFoundException.class)
	public void searchOneApplicationFailTest() throws Exception {
		applicationService.findApplication((long) 1);
	}

	@Test
	public void changeStatus() {
		List<Application> applications = applicationService.findAllApplications();
		StatusDTO status = new StatusDTO("accepted", applications.get(0).getVersion());
		Application application = applicationService.changeStatus(applications.get(0).getId(), status);
		assertEquals(application.getStatus().getName().toString(), status.getName().toString());
	}

	@Test(expected = StatusNotFoundException.class)
	public void changeStatusFail() {
		List<Application> applications = applicationService.findAllApplications();
		StatusDTO status = new StatusDTO("bruno", applications.get(0).getVersion());
		applicationService.changeStatus(applications.get(0).getId(), status);
	}

	@SuppressWarnings("unused")
	@Test(expected = OutdatedApplicationException.class)
	public void simultaniousUpdateTest() {
		List<Application> user1 = applicationService.findAllApplications();
		List<Application> user2 = applicationService.findAllApplications();
		StatusDTO status1 = new StatusDTO("accepted", user1.get(0).getVersion());
		StatusDTO status2 = new StatusDTO("rejected", user2.get(0).getVersion());
		Application application1 = applicationService.changeStatus(user1.get(0).getId(), status1);
		Application application2 = applicationService.changeStatus(user2.get(0).getId(), status2);
	}

	@After
	public void destruct() {
		applicationRepo.deleteAll();
	}

}
