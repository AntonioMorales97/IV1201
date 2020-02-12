package se.kth.iv1201.recruitmentbackend.application;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import se.kth.iv1201.recruitmentbackend.domain.Application;
import se.kth.iv1201.recruitmentbackend.domain.Availability;
import se.kth.iv1201.recruitmentbackend.domain.Competence;
import se.kth.iv1201.recruitmentbackend.domain.CompetenceProfile;
import se.kth.iv1201.recruitmentbackend.domain.Person;
import se.kth.iv1201.recruitmentbackend.domain.Role;
import se.kth.iv1201.recruitmentbackend.domain.Status;
import se.kth.iv1201.recruitmentbackend.repository.ApplicationRepository;
import se.kth.iv1201.recruitmentbackend.repository.AvailabilityRepository;
import se.kth.iv1201.recruitmentbackend.repository.CompetenceProfileRepository;
import se.kth.iv1201.recruitmentbackend.repository.CompetenceRepository;
import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;
import se.kth.iv1201.recruitmentbackend.repository.RoleRepository;
import se.kth.iv1201.recruitmentbackend.repository.StatusRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ApplicationServiceTest {
	@Autowired
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
	ApplicationRepository applicationRepo;
	@Autowired
	CompetenceProfileRepository compPRepo;
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	ApplicationService applicationService;
	private Person p1; 
	private Role r1;
	private Role r2;
	private Competence c1;
	private Competence c2;
	//private Status s1;
	//private Status s2;
	//private Status s3;
	private Application a1;
	private Availability av1; 
		
	private CompetenceProfile cp2;
	/**
	 * Sets up a dummy data for an Application
	 */
	@Before
	public void setupUser() {
		
		r1 = new Role("recruit");
		r2= new Role("applicant");
		//s1 = new Status("accepted");
		//s2 = new Status("unhandled");
		//s3 = new Status("rejected");
		c1 = new Competence("Karuselldrift");
		c2 = new Competence("Korvgrillning");
		p1 = new Person("fa", "fa", "fa@gmail.com", "1948281092","fa", encoder.encode("123"), r1);
		a1 = new Application(statusRepo.findByName("unhandled").get(),personRepo.findByUsername("fa"));
		cp2= new CompetenceProfile(a1,c2, 2.0);
		av1 = new Availability(a1, new Date(2014/02/23),new Date(2014/05/25)); 
		  
		/*r1 = new Role("recruit");
		r2= new Role("applicant");
		s1 = new Status("accepted");
		s2 = new Status("unhandled");
		s3 = new Status("rejected");
		c1 = new Competence("Karuselldrift");
		c2 = new Competence("Korvgrillning");
		p1 = new Person("fa", "fa", "fa@gmail.com", "1948281092","fa", encoder.encode("123"), roleRepo.getOne((long) 2));
		a1 = new Application(statusRepo.findByName("unhandled").get(),p1);
		cp2= new CompetenceProfile(a1,competenceRepo.findById((long) 2).get(), 2.0);
		av1 = new Availability(a1, new Date(2014/02/23),new Date(2014/05/25));*/
		
		
	}
	@Test
	public void searchAllApplicationsTest() {
		setupTestData();
		List<Application> applications =applicationService.findAllApplications();

		assertEquals(applications.get(0).toString(), "Application(id=1, createDate=2020-02-08 20:32:51.161, version=2, status=Status(id=1, name=accepted), person=Person(id=1, firstName=fa, lastName=fa, email=fa@gmail.com, ssn=1948281092, username=fa, password=123, role=Role(id=1, name=recruit)), availability=[Availability(id=1, fromDate=1970-01-01, toDate=1970-01-01)], competenceProfile=[CompetenceProfile(id=1, competence=Competence(id=2, name=Korvgrillning), yearsOfExperience=2.0)])");
		
	}
	
	private void setupTestData() {
		
		roleRepo.save(r1);
		roleRepo.save(r2);
		personRepo.save(p1);
		competenceRepo.save(c1);
		competenceRepo.save(c2);
		//statusRepo.save(s1);
		//statusRepo.save(s2);
		//statusRepo.save(s3);
		applicationRepo.save(a1);
		a1.getCompetenceProfile().add(cp2);
		compPRepo.save(cp2);
		availabilityRepo.save(av1);
		a1.getAvailability().add(av1);
		
		
		 
		/*roleRepo.save(r1);
		roleRepo.save(r2);
		personRepo.save(p1);
		competenceRepo.save(c1);
		competenceRepo.save(c2);
		statusRepo.save(s1);
		statusRepo.save(s2);
		statusRepo.save(s3);
		applicationRepo.save(a1);
		a1.getCompetenceProfile().add(cp2);
		compPRepo.save(cp2);
		availabilityRepo.save(av1);
		a1.getAvailability().add(av1);*/
	}
	
}
