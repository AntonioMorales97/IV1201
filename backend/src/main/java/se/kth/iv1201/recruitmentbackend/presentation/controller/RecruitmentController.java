package se.kth.iv1201.recruitmentbackend.presentation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import se.kth.iv1201.recruitmentbackend.application.RecruitmentService;
import se.kth.iv1201.recruitmentbackend.domain.Person;
import se.kth.iv1201.recruitmentbackend.presentation.dto.PersonDTO;

@RestController
@CrossOrigin
public class RecruitmentController {
	@Autowired
	RecruitmentService recruitmentSerivce;
	

	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Person registerPerson(@RequestBody @Valid PersonDTO personDTO, HttpServletRequest request) {
		Person person = this.recruitmentSerivce.registerUser(personDTO); 
		return person;
	}
	
}
