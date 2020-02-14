package se.kth.iv1201.recruitmentbackend.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import se.kth.iv1201.recruitmentbackend.domain.Person;
/**
 * Implements UserDetails, used to authentication of Persons.
 *
 */
public class MyUserDetails implements UserDetails{
	private static final long serialVersionUID = 1L;
		
	private Person person;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities; 
	
	public MyUserDetails(Person person) {
		this.person= person;
		this.username = person.getUsername();
		this.password = person.getPassword();
		this.authorities= translate(person.getRole().getName());
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * Password get method
	 * @return the password of the user.
	 */
	@Override
	public String getPassword() {
	
		return this.password;
	}

	/**
	 * Username get method.
	 * @return the username of the user.
	 */
	@Override
	public String getUsername() {
		
		return this.username;
	}

	/**
	 * Required by UserDetails
	 * Not used by the application
	 * @return true
	 */
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	/**
	 * Required by UserDetails
	 * Not used by the application
	 * @return true
	 */
	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	/**
	 * Required by UserDetails
	 * Not used by the application
	 * @return true
	 */
	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	/**
	 * Required by UserDetails
	 * Not used by the application
	 * @return true
	 */
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
	public String getRole() {
		return this.person.getRole().getName();
	
	}
	private Collection<? extends GrantedAuthority> translate(String role) { 
		List<GrantedAuthority> authorities = new ArrayList<>(); 
			String name = role.toUpperCase(); 
			if (!name.startsWith("ROLE_")) { 
				name = "ROLE_" + name; 
			} 
			authorities.add(new SimpleGrantedAuthority(name)); 
		return authorities; 
	} 
}
