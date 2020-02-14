package se.kth.iv1201.recruitmentbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class representing the spring boot application
 */
@SpringBootApplication
public class RecruitmentBackendApplication {
	/**
	 * Main method to start the backend application
	 * @param args list of option arguments used when starting the application.
	 */
	public static void main(String[] args) {

		SpringApplication.run(RecruitmentBackendApplication.class, args);
	}
}