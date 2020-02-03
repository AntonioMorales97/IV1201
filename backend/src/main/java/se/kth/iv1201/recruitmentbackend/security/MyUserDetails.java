package se.kth.iv1201.recruitmentbackend.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import se.kth.iv1201.recruitmentbackend.domain.Person;
/**
 * Implements UserDetails, used to authentication of Persons.
 *
 */
public class MyUserDetails implements UserDetails{
	private static final long serialVersionUID = 1L;
		
	private Person person;
	
	public MyUserDetails(Person person) {
		this.person= person;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	@Override
	public String getPassword() {
	
		return this.person.getPassword();
	}

	@Override
	public String getUsername() {
		
		return this.person.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}
	/**
	 * @return the stored <code>Person</code> in this principal.
	 */
	public Person getPerson() {
		return this.person;
	}
	/**
	 * Gets the role of the current person, used to authorization.
	 */
	/*public getRole() {
		this.person.getRole();
	}*/
}
