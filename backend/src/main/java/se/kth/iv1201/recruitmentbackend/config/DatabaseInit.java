package se.kth.iv1201.recruitmentbackend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import se.kth.iv1201.recruitmentbackend.domain.Person;
import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;

/**
 * Class used to initialize  the database with some data if need be.
 *
 */
@Configuration
@Slf4j
public class DatabaseInit {

	@Bean
	CommandLineRunner initializeDatabase(PersonRepository personRepo) {
		return null;
		//Person p1 = new Person("testyy","testaryy","testay@gmail.com","9443528491","heja","då");
		//return args ->{
			//log.info("Inserting " + personRepo.save(p1));
		//};
		
	}
}
