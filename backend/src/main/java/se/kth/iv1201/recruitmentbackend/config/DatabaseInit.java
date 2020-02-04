package se.kth.iv1201.recruitmentbackend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import se.kth.iv1201.recruitmentbackend.domain.Availability;
import se.kth.iv1201.recruitmentbackend.domain.Competence;
import se.kth.iv1201.recruitmentbackend.domain.CompetenceProfile;
import se.kth.iv1201.recruitmentbackend.domain.Person;
import se.kth.iv1201.recruitmentbackend.domain.Role;
import se.kth.iv1201.recruitmentbackend.repository.AvailabilityRepository;
import se.kth.iv1201.recruitmentbackend.repository.CompetenceProfileRepository;
import se.kth.iv1201.recruitmentbackend.repository.CompetenceRepository;
import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;
import se.kth.iv1201.recruitmentbackend.repository.RoleRepository;

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
			AvailabilityRepository availabilityRepo, CompetenceProfileRepository compPRepo) {
		return null;
		
		//FOP TESTING PURPOUSES LOCALLY
		/*return args->{
		Role r1 = new Role("recruit");
		Role r2 = new Role("applicant");
		roleRepo.save(r1);
		roleRepo.save(r2);
		Competence c1 = new Competence("Karuselldrift");
		Competence c2 = new Competence("Korvgrillning");
		competenceRepo.save(c1);
		competenceRepo.save(c2);
		Person p2 = personRepo.findByUsername("flunsasa");
		
		Optional<Competence> c3 = competenceRepo.findById((long) 2);
		System.out.println(c3.get());
		CompetenceProfile cp2= new CompetenceProfile(p2, c3.get(), 2.0);
		compPRepo.save(cp2);
		p2.getCompetenceProfile().add(cp2);
			Availability a1 = new Availability(p2, new Date(2014/02/23),new Date(2014/05/25));
			availabilityRepo.save(a1);
			p2.getAvailability().add(a1);
		};*/
		/*
		Optional<Competence> c2 = competenceRepo.findById((long) 1);
		CompetenceProfile cp2= new CompetenceProfile(p2, c2.get(), 2.0);
		return args->{
			log.info("inserting "+ compPRepo.save(cp2));
		
		};*/
		
	}
}

