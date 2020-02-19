package se.kth.iv1201.recruitmentbackend.jwt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import se.kth.iv1201.recruitmentbackend.application.RecruitmentService;
import se.kth.iv1201.recruitmentbackend.application.exception.IllegalTransactionException;
import se.kth.iv1201.recruitmentbackend.domain.Role;
import se.kth.iv1201.recruitmentbackend.presentation.dto.PersonDTO;
import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;
import se.kth.iv1201.recruitmentbackend.repository.RoleRepository;
import se.kth.iv1201.recruitmentbackend.security.MyUserDetailsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTokenUtilTests {

	@Autowired
	RecruitmentService recruitmentService;

	@Autowired
	MyUserDetailsService userDetailsService;

	@Autowired
	JwtTokenUtil jwtUtil;

	@Autowired
	PersonRepository personRepo;

	@Autowired
	RoleRepository roleRepo;

	private static PersonDTO dummyPerson;

	@BeforeClass
	public static void setup() {
		dummyPerson = new PersonDTO("taget", "taget", "jwtTest@gmail.com", "9901039482", "jwtTester", "Detkanske");
	}

	@Before
	public void construct() {
		personRepo.deleteAll();
		roleRepo.deleteAll();
	}

	@Test
	public void createJWTTest() {
		UserDetails user = register(dummyPerson);
		String jwt = jwtUtil.createToken(user);
		String username = jwtUtil.getTokenUsername(jwt);
		assertEquals(username, dummyPerson.getUsername());

	}

	@Test
	public void checkJWTRoleTest() {
		UserDetails user = register(dummyPerson);
		String jwt = jwtUtil.createToken(user);
		String role = jwtUtil.getTokenRole(jwt);
		assertEquals(role, "APPLICANT");
	}

	@Test
	public void getExpireDateTest() {
		UserDetails user = register(dummyPerson);
		String jwt = jwtUtil.createToken(user);
		Date expire = jwtUtil.getTokenExpirationDate(jwt);
		assertNotNull(expire);
	}

	@Test
	public void validateTokenTest() {
		UserDetails user = register(dummyPerson);
		String jwt = jwtUtil.createToken(user);
		Boolean test = jwtUtil.validateToken(jwt, user);
		assertTrue(test);
	}

	@Test
	public void validateTokenFailTest() {
		UserDetails user = register(dummyPerson);
		PersonDTO failPerson = new PersonDTO("taget", "taget", "jwtFail@gmail.com", "9901939482", "jwtFail",
				"Detkanske");
		UserDetails userFail = registerFail(failPerson);
		String jwt = jwtUtil.createToken(user);
		Boolean test = jwtUtil.validateToken(jwt, userFail);
		assertFalse(test);
	}

	@After
	public void deConstruct() {
		personRepo.deleteAll();
		roleRepo.deleteAll();
	}

	private UserDetails registerFail(PersonDTO failPerson) {
		recruitmentService.registerUser(failPerson);
		return userDetailsService.loadUserByUsername(failPerson.getUsername());
	}

	private UserDetails register(PersonDTO person) throws IllegalTransactionException {
		roleRepo.save(new Role("applicant"));
		recruitmentService.registerUser(person);
		return userDetailsService.loadUserByUsername(person.getUsername());
	}
}
