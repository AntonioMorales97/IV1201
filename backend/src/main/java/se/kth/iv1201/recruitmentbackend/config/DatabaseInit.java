package se.kth.iv1201.recruitmentbackend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
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

import java.util.Date;
import java.util.Optional;
/**
 * Class used to initialize  the database with some data if need be.
 *
 */
@SuppressWarnings("unused")
@Configuration
@Slf4j

public class DatabaseInit {


	@Bean
	CommandLineRunner initializeDatabase(PersonRepository personRepo, CompetenceRepository competenceRepo, RoleRepository roleRepo,
			AvailabilityRepository availabilityRepo, CompetenceProfileRepository compPRepo, StatusRepository statusRepo, ApplicationRepository applicationRepo) {
		return null;
	
		//FOP TESTING PURPOUSES LOCALLY
		/*return args->{				
		Role r1 = new Role("recruit");
		Role r2 = new Role("applicant");
		roleRepo.save(r1);
		roleRepo.save(r2);
		Person p2 = new Person("fa", "fa", "fa@gmail.com", "1948281092","fa", "123", roleRepo.findById((long) 1).get());
		personRepo.save(p2);
		Competence c1 = new Competence("Karuselldrift");
		Competence c2 = new Competence("Korvgrillning");
		competenceRepo.save(c1);
		competenceRepo.save(c2);
		Status s1 = new Status("accepted");
		Status s2 = new Status("unhandled");
		Status s3 = new Status("rejected");
		statusRepo.save(s1);
		statusRepo.save(s2);
		statusRepo.save(s3);
		Optional<Competence> c3 = competenceRepo.findById((long) 2);
		Application a1 = new Application(statusRepo.findByName("unhandled"),personRepo.findByUsername("fa"));
		
		applicationRepo.save(a1);
				
		CompetenceProfile cp2= new CompetenceProfile(a1, c3.get(), 2.0);
		compPRepo.save(cp2);
		a1.getCompetenceProfile().add(cp2);
		Availability av1 = new Availability(a1, new Date(2014/02/23),new Date(2014/05/25));
		availabilityRepo.save(av1);
		a1.getAvailability().add(av1);
		};
		*/
	}
}

