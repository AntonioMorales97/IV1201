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
import se.kth.iv1201.recruitmentbackend.security.MyUserDetailsService;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired 
	private JwtRequestFilter jwtReqFilter;

	@Autowired
	private JwtExceptionHandlerFilter jwtExcFilter;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/register", "/", "/authenticate");
	}
	/**
	 *  configure so that AuthenticationManager knows where to load users to match credentials to.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
		authentication.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
	}
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf().disable() // disables CSRF
		.authorizeRequests()
		.antMatchers("/**")
		.authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // sessions wont be used to store user state.
		.and()
		.addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class) // user the jwtReqFilter.
		.addFilterBefore(jwtExcFilter, JwtRequestFilter.class); // add exception handler.
	}
	/**
	 * Sets up bean for encryption of passwords.
	 * @return Password Encoder.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(13);
	}
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
	 * Disables <code>JwtExceptionHandlerFilter</code> to register outside security chain.
	 * 
	 * @param filter The filter.
	 * @return the filter registration bean.
	 */
	@Bean
	public FilterRegistrationBean<JwtExceptionHandlerFilter> jwtExcFilterRegistration(JwtExceptionHandlerFilter jwtExcFilter) {
		FilterRegistrationBean<JwtExceptionHandlerFilter> registration = new FilterRegistrationBean<JwtExceptionHandlerFilter>(jwtExcFilter);
		registration.setEnabled(false);
		return registration;
	}
	
}
