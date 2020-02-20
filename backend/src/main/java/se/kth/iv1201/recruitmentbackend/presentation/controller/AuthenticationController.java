package se.kth.iv1201.recruitmentbackend.presentation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import se.kth.iv1201.recruitmentbackend.jwt.JwtTokenUtil;
import se.kth.iv1201.recruitmentbackend.presentation.exception.InvalidCredentialsException;
import se.kth.iv1201.recruitmentbackend.presentation.models.LoginForm;
import se.kth.iv1201.recruitmentbackend.presentation.models.LoginResponse;
import se.kth.iv1201.recruitmentbackend.security.MyUserDetailsService;

/**
 * Class that handles all the authentication of users.
 *
 */
@RestController
@Validated
@CrossOrigin
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtUtil;

	/**
	 * Handles login requests to the REST API.
	 * 
	 * @param loginForm A <code>LoginForm</code> that holds user information.
	 * @return a <code>LoginResponse</code> embedded and HTTP 200.
	 * @throws Exception if login attempt failed.
	 */
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@Valid @RequestBody LoginForm loginForm) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
		} catch (BadCredentialsException ex) {
			throw new InvalidCredentialsException("Invalid credentials, please try again!");
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginForm.getUsername());
		String token = jwtUtil.createToken(userDetails);
		return ResponseEntity.ok(new LoginResponse(token, jwtUtil.getTokenRole(token)));
	}
}
