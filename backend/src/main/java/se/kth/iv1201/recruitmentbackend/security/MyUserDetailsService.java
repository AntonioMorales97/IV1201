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
 * Implements <code>UserDetailsService</code>. Used to authenticate <code>Person</code>s.
 *
 */
@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private PersonRepository personRepo;

	/**
	 * Provides <code>UserDetails</code> for a provided username.
	 * 
	 * @param username The username of the <code>person</code>.
	 * @return a <code>UserDetails</code> object representing the user.
	 * @throws <code>UsernameNotFoundException</code> if the requested user could not be found.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person person = this.personRepo.findByUsername(username);

		if (person == null)
			throw new UsernameNotFoundException("person with username: " + username + ", was not found!");

		return new MyUserDetails(person);
	}
}