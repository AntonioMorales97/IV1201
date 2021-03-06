package se.kth.iv1201.recruitmentbackend.presentation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import se.kth.iv1201.recruitmentbackend.application.RecruitmentService;
import se.kth.iv1201.recruitmentbackend.presentation.dto.PersonDTO;

/**
 * Controller to handle the registration for new applicants.
 * 
 * @author Gurk1
 *
 */
@RestController
@Validated
@CrossOrigin
public class RecruitmentController {

	@Autowired
	RecruitmentService recruitmentSerivce;
	
	final static String REGISTER_URL = "/register";
	/**
	 * Handles a new request for a registration.
	 * 
	 * @param personDTO The information of the newly registered person.
	 * @param request   the <code>HttpServletRequest</code>.
	 * @return HTTP 200 OK if successful.
	 */
	@PostMapping(REGISTER_URL)
	@ResponseStatus(HttpStatus.CREATED)
	public void registerPerson(@RequestBody @Valid PersonDTO personDTO, HttpServletRequest request) {
		this.recruitmentSerivce.registerUser(personDTO);
	}

}
