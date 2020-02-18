package se.kth.iv1201.recruitmentbackend.migration;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import se.kth.iv1201.recruitmentbackend.domain.Application;
import se.kth.iv1201.recruitmentbackend.domain.Availability;
import se.kth.iv1201.recruitmentbackend.domain.Competence;
import se.kth.iv1201.recruitmentbackend.domain.CompetenceProfile;
import se.kth.iv1201.recruitmentbackend.domain.Person;
import se.kth.iv1201.recruitmentbackend.domain.Role;
import se.kth.iv1201.recruitmentbackend.domain.Status;
import se.kth.iv1201.recruitmentbackend.migration.dto.AvailabilityDTO;
import se.kth.iv1201.recruitmentbackend.migration.dto.CompetenceDTO;
import se.kth.iv1201.recruitmentbackend.migration.dto.CompetenceProfileDTO;
import se.kth.iv1201.recruitmentbackend.migration.dto.PersonDTO;
import se.kth.iv1201.recruitmentbackend.migration.dto.RoleDTO;
import se.kth.iv1201.recruitmentbackend.migration.integration.OldRecruitmentDAO;
import se.kth.iv1201.recruitmentbackend.repository.ApplicationRepository;
import se.kth.iv1201.recruitmentbackend.repository.CompetenceRepository;
import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;
import se.kth.iv1201.recruitmentbackend.repository.RoleRepository;
import se.kth.iv1201.recruitmentbackend.repository.StatusRepository;

/**
 * A service used to migrate from the old database to the new one.
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class MigrationService {
	private static final String RECRUIT_ROLE = "recruit";
	private static final String UNHANDLED_STATUS = "unhandled";
	private OldRecruitmentDAO oldRecruitmentDAO;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private CompetenceRepository competenceRepo;
	
	@Autowired
	private PersonRepository personRepo;
	
	@Autowired
	private StatusRepository statusRepo;
	
	@Autowired
	private ApplicationRepository applicationRepo;

	@Autowired
	private MailSender emailSender;


	/**
	 * Preform a migration from the old database.
	 */
	public void migrate() {
		this.oldRecruitmentDAO = new OldRecruitmentDAO();

		insertCompetences();
		insertRoles();
		insertPersons();
		oldRecruitmentDAO.closeConnection();
	}

	/**
	 * Fetches the person entries from the old database and inserts them into the new one.
	 * This method also deals with missing data in the old tables to the extent it is possible.
	 */
	private void insertPersons() {
		List<PersonDTO> personDTOs = this.oldRecruitmentDAO.getPersons();
		
		personDTOs.forEach((personDTO) -> {
			if(personDTO.getRole().equals(RECRUIT_ROLE)) {
				Person recruit = new Person(personDTO.getName(), personDTO.getSurname(), personDTO.getEmail(), personDTO.getSsn(), personDTO.getUsername(), encoder.encode(personDTO.getPassword()), roleRepo.findByName(personDTO.getRole()));
				personRepo.save(recruit);
			} else {
				String username;
				String password;
				if(personDTO.getUsername() == null) {
					username = personDTO.getEmail();
				} else {
					username = personDTO.getUsername();
				}
				
				if(personDTO.getPassword() == null) {
					String newPass = UUID.randomUUID().toString();
					password = encoder.encode(newPass);
					emailSender.sendCredentials(username, newPass, personDTO.getEmail()); //Sends an email with the new password if it is missing
				} else {
					password = personDTO.getPassword();
				}
				
				Person applicant = new Person(personDTO.getName(), personDTO.getSurname(), personDTO.getEmail(), personDTO.getSsn(), username, password, roleRepo.findByName(personDTO.getRole()));
				//Set<Availability> applicantAvailabilities = applicant.getAvailability();
				//Set<CompetenceProfile> applicantCompetenceProfiles = applicant.getCompetenceProfile();
				
				List<AvailabilityDTO> availabilityDTOs = oldRecruitmentDAO.getAvailabilities(personDTO.getId());
				List<CompetenceProfileDTO> competenceProfileDTOs = oldRecruitmentDAO.getCompetenceProfiles(personDTO.getId());
				
				if(!availabilityDTOs.isEmpty() || !competenceProfileDTOs.isEmpty()) {
					Optional<Status> unhandled = statusRepo.findByName(UNHANDLED_STATUS);
					Application application = new Application(unhandled.get(), applicant);
					Set<Availability> applicantAvailabilities = application.getAvailability();
					Set<CompetenceProfile> applicantCompetenceProfiles = application.getCompetenceProfile();
					
					availabilityDTOs.forEach((availabilityDTO) -> {
						Availability availability = new Availability(application, availabilityDTO.getFrom(), availabilityDTO.getTo());
						applicantAvailabilities.add(availability);
					});
					
					competenceProfileDTOs.forEach((competenceProfileDTO) -> {
						CompetenceProfile competenceProfile = new CompetenceProfile(application, competenceRepo.findByName(competenceProfileDTO.getName()), competenceProfileDTO.getYearsOfExperience());
						applicantCompetenceProfiles.add(competenceProfile);
					});
					
					personRepo.save(applicant);
					applicationRepo.save(application);
				} else {
					personRepo.save(applicant);
				}
			}
		});
	}

	/**
	 * Fetches Competence entries from the old database and inserts them into the new one.
	 */
	private void insertCompetences() {
		List<CompetenceDTO> competenceDTOs = this.oldRecruitmentDAO.getCompetences();
		
		competenceDTOs.forEach((competenceDTO) -> {
			if(competenceRepo.findByName(competenceDTO.getName()) == null) {
				competenceRepo.save(new Competence(competenceDTO.getName()));
			}
		});
	}

	/**
	 * Fetches roles from the old database and inserts them into the new one.
	 */
	private void insertRoles() {
		List<RoleDTO> roleDTOs = this.oldRecruitmentDAO.getRoles();
		
		roleDTOs.forEach((roleDTO) -> {
			if(roleRepo.findByName(roleDTO.getName()) == null) {
				roleRepo.save(new Role(roleDTO.getName()));
			}
		});
	}
}
