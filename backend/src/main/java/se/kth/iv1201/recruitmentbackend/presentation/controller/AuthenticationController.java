package se.kth.iv1201.recruitmentbackend.presentation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import se.kth.iv1201.recruitmentbackend.application.exception.IllegalTransactionException;
import se.kth.iv1201.recruitmentbackend.jwt.JwtTokenUtil;
import se.kth.iv1201.recruitmentbackend.presentation.models.LoginForm;
import se.kth.iv1201.recruitmentbackend.presentation.models.LoginResponse;
import se.kth.iv1201.recruitmentbackend.security.MyUserDetailsService;

/**
 * Class that handles all the authentication of Users.
 *
 */
@RestController
@Validated
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtUtil;
	
	
	/**
	 * Handles login requests to the rest api.
	 * @param loginForm The user information.
	 * @return LoginResponse.
	 * @throws Exception
	 */
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate (@Valid @RequestBody LoginForm loginForm) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
		}catch(DisabledException ex) {
			throw new IllegalTransactionException("Account is disabled!",4);
		}catch(BadCredentialsException ex) {
			throw new IllegalTransactionException("Invalid credentials, please try again!",5);
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginForm.getUsername());
		String token = jwtUtil.createToken(userDetails);
		return ResponseEntity.ok(new LoginResponse(token, jwtUtil.getTokenRole(token)));
	}
}
