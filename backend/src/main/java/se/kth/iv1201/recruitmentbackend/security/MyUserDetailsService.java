package se.kth.iv1201.recruitmentbackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitmentbackend.domain.Person;
import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;

/**
 * Implements UserDetailsService. Used to authenticate Persons.
 *
 */
@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private PersonRepository personRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person person = this.personRepo.findByUsername(username);
		if(person == null)
			throw new UsernameNotFoundException("person with username: " + username + ", was not found!");

		return new MyUserDetails(person);
	}
}