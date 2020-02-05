package se.kth.iv1201.recruitmentbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import se.kth.iv1201.recruitmentbackend.domain.Role;
import se.kth.iv1201.recruitmentbackend.repository.RoleRepository;

@SpringBootApplication
public class RecruitmentBackendApplication {

	
	public static void main(String[] args) {
		
		SpringApplication.run(RecruitmentBackendApplication.class, args);
	}



	

}
