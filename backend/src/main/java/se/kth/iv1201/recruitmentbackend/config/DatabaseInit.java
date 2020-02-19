package se.kth.iv1201.recruitmentbackend.config;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import se.kth.iv1201.recruitmentbackend.domain.Competence;
import se.kth.iv1201.recruitmentbackend.domain.Person;
import se.kth.iv1201.recruitmentbackend.domain.Role;
import se.kth.iv1201.recruitmentbackend.domain.Status;
import se.kth.iv1201.recruitmentbackend.migration.MigrationService;
import se.kth.iv1201.recruitmentbackend.repository.ApplicationRepository;
import se.kth.iv1201.recruitmentbackend.repository.AvailabilityRepository;
import se.kth.iv1201.recruitmentbackend.repository.CompetenceProfileRepository;
import se.kth.iv1201.recruitmentbackend.repository.CompetenceRepository;
import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;
import se.kth.iv1201.recruitmentbackend.repository.RoleRepository;
import se.kth.iv1201.recruitmentbackend.repository.StatusRepository;

/**
 * Class used to initialize the database with some data if need be.
 *
 */
@Configuration
public class DatabaseInit {
	private static final String UNHANDLED_STATUS = "unhandled";
	private static final String ACCEPTED_STATUS = "accepted";
	private static final String REJECTED_STATUS = "rejected";

	@Autowired
	private MigrationService migration;

	@Bean
	CommandLineRunner initializeDatabase(PersonRepository personRepo, CompetenceRepository competenceRepo,
			RoleRepository roleRepo, AvailabilityRepository availabilityRepo, CompetenceProfileRepository compPRepo,
			StatusRepository statusRepo, ApplicationRepository applicationRepo) {
		return args -> {
			Optional<Status> optAccepted = statusRepo.findByName(ACCEPTED_STATUS);
			if (optAccepted.isEmpty()) {
				Status accepted = new Status(ACCEPTED_STATUS);
				statusRepo.save(accepted);
			}

			Optional<Status> optRejected = statusRepo.findByName(REJECTED_STATUS);
			if (optRejected.isEmpty()) {
				Status rejected = new Status(REJECTED_STATUS);
				statusRepo.save(rejected);
			}

			Optional<Status> optUnhandled = statusRepo.findByName(UNHANDLED_STATUS);
			if (optUnhandled.isEmpty()) {
				Status unhandled = new Status(UNHANDLED_STATUS);
				statusRepo.save(unhandled);
			}

			List<Person> persons = personRepo.findAll();
			List<Competence> competences = competenceRepo.findAll();
			List<Role> roles = roleRepo.findAll();
			if (persons.isEmpty() && competences.isEmpty() && roles.isEmpty()) {
				try {
					migration.migrate();
				} catch (SQLException | NullPointerException e) {
					System.err.println("Could not connect to old database for migration");
				}
			}
		};

		// FOP TESTING PURPOUSES LOCALLY
		/*
		 * return args->{ Role r1 = new Role("recruit"); Role r2 = new
		 * Role("applicant"); roleRepo.save(r1); roleRepo.save(r2); Person p2 = new
		 * Person("fa", "fa", "fa@gmail.com", "1948281092","fa", "123",
		 * roleRepo.findById((long) 1).get()); personRepo.save(p2); Competence c1 = new
		 * Competence("Karuselldrift"); Competence c2 = new Competence("Korvgrillning");
		 * competenceRepo.save(c1); competenceRepo.save(c2); Status s1 = new
		 * Status("accepted"); Status s2 = new Status("unhandled"); Status s3 = new
		 * Status("rejected"); statusRepo.save(s1); statusRepo.save(s2);
		 * statusRepo.save(s3); Optional<Competence> c3 = competenceRepo.findById((long)
		 * 2); Application a1 = new
		 * Application(statusRepo.findByName("unhandled").get(),personRepo.
		 * findByUsername("fa"));
		 * 
		 * applicationRepo.save(a1);
		 * 
		 * CompetenceProfile cp2= new CompetenceProfile(a1, c3.get(), 2.0);
		 * compPRepo.save(cp2); a1.getCompetenceProfile().add(cp2); Availability av1 =
		 * new Availability(a1, new Date(2014/02/23),new Date(2014/05/25));
		 * availabilityRepo.save(av1); a1.getAvailability().add(av1); };
		 */

	}
}
