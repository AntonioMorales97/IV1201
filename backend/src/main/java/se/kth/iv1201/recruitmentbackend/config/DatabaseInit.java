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
import se.kth.iv1201.recruitmentbackend.enums.ApplicationStatus;
import se.kth.iv1201.recruitmentbackend.migration.MigrationService;
import se.kth.iv1201.recruitmentbackend.repository.CompetenceRepository;
import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;
import se.kth.iv1201.recruitmentbackend.repository.RoleRepository;
import se.kth.iv1201.recruitmentbackend.repository.StatusRepository;

/**
 * Class used to initialize the database with some data if needed.
 *
 */
@Configuration
public class DatabaseInit {
	private static final String UNHANDLED_STATUS = ApplicationStatus.UNHANDLED.getStatus();
	private static final String ACCEPTED_STATUS = ApplicationStatus.ACCEPTED.getStatus();
	private static final String REJECTED_STATUS = ApplicationStatus.REJECTED.getStatus();
	
	@Autowired
	private MigrationService migration;

	/**
	 * Initializes the used database if necessary.
	 * 
	 * @param personRepo <code>PersonRepository</code>.
	 * @param competenceRepo <code>CompetenceRepository</code>.
	 * @param roleRepo <code>RoleRepository</code>.
	 * @param statusRepo <code>StatusRepository</code>.
	 * @return a <code>CommandLineRunner</code> for init.
	 */
	@Bean
	CommandLineRunner initializeDatabase(PersonRepository personRepo, CompetenceRepository competenceRepo,
			RoleRepository roleRepo,
			StatusRepository statusRepo) {
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
	}
}
