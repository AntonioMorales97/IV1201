package se.kth.iv1201.recruitmentbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import se.kth.iv1201.recruitmentbackend.jwt.filters.JwtExceptionHandlerFilter;
import se.kth.iv1201.recruitmentbackend.jwt.filters.JwtRequestFilter;
import se.kth.iv1201.recruitmentbackend.presentation.controller.RecruitmentController;
import se.kth.iv1201.recruitmentbackend.security.MyUserDetailsService;

/**
 * Sets up spring security with custom configurations.
 *
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String RECRUIT_ROLE = "RECRUIT";
	private static final  String REGISTER_URL = "/register";
	private static final  String AUTH_URL = "/authenticate";
	static final String APPLICATIONS_URL = "/applications";
	static final String APPLICATION_URL = "/application/*";
	static final String ALTER_STATUS_URL = "alter-status/*";
	static final String ALL_URL = "/**";

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtReqFilter;

	@Autowired
	private JwtExceptionHandlerFilter jwtExcFilter;
	

	/**
	 * Configures so that <code>AuthenticationManager</code> knows where to load
	 * users to match credentials to.
	 * 
	 * @param authentication The <code>AuthenticationManagerBuilder</code>.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
		authentication.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
	}

	/**
	 * A layer above HttpSecurity. Opens up /authentication and /register.
	 * 
	 * @param web The <code>WebSecurity</code> to configure.
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
	
		web.ignoring().antMatchers(AUTH_URL, REGISTER_URL);
	}

	/**
	 * Layer below WebSecurity. Sets up security against the API and adds filters.
	 * 
	 * @param httpSecurity The <code>HttpSecurity</code> to configure.
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().cors().and().authorizeRequests()
				.antMatchers(APPLICATION_URL, APPLICATIONS_URL, ALTER_STATUS_URL).hasRole(RECRUIT_ROLE)
				.antMatchers(ALL_URL).authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(jwtExcFilter, JwtRequestFilter.class);
	}

	/**
	 * Sets up bean for encryption of passwords.
	 * 
	 * @return Password Encoder.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(13);
	}

	/**
	 * Sets up the authenticationManager
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * Disables <code>JwtRequestFilter</code> to register outside security chain.
	 * 
	 * @param filter The filter.
	 * @return the filter registration bean.
	 */
	@Bean
	public FilterRegistrationBean<JwtRequestFilter> jwtFilterRegistration(JwtRequestFilter jwtFilter) {
		FilterRegistrationBean<JwtRequestFilter> registration = new FilterRegistrationBean<JwtRequestFilter>(jwtFilter);
		registration.setEnabled(false);

		return registration;
	}

	/**
	 * Disables <code>JwtExceptionHandlerFilter</code> to register outside security
	 * chain.
	 * 
	 * @param filter The filter.
	 * @return the filter registration bean.
	 */
	@Bean
	public FilterRegistrationBean<JwtExceptionHandlerFilter> jwtExcFilterRegistration(
			JwtExceptionHandlerFilter jwtExcFilter) {
		FilterRegistrationBean<JwtExceptionHandlerFilter> registration = new FilterRegistrationBean<JwtExceptionHandlerFilter>(
				jwtExcFilter);
		registration.setEnabled(false);
		return registration;
	}
}
