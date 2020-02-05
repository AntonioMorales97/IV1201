package se.kth.iv1201.recruitmentbackend.presentation;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import se.kth.iv1201.recruitmentbackend.domain.Role;
import se.kth.iv1201.recruitmentbackend.presentation.models.LoginResponse;
import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;
import se.kth.iv1201.recruitmentbackend.repository.RoleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class AuthenticationControllerTest {
	@Autowired
	private MockMvc mvc;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	PersonRepository personRepo;
	@Autowired
	ObjectMapper objectMapper;
	@Value("${jwt.secret}")
	private String secret;

	/**
	 * Test that should fail.
	 * @throws Exception
	 */
	@Test
	public void registerTestBadRequest()throws Exception{
		this.mvc.perform(post("/authenticate")).andDo(print()).andExpect(status().isBadRequest());
	}
	

	
	@Test
	public void registerTestSuccess()throws Exception{ 
		String user = setupUser("heja", "då");
		MvcResult res = this.mvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON)
                .content(user)).andDo(print())
                .andExpect(status().isOk()).andReturn();
		String result = res.getResponse().getContentAsString();
		LoginResponse response = objectMapper.readValue(result, LoginResponse.class);
		String token = response.getJwtToken();
		Jws<Claims> jws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
		System.out.println(jws.getBody().getSubject());
		assertEquals("heja", jws.getBody().getSubject()); 
	}
	
	
	private String setupUser(String username, String password) throws JSONException {
		 JSONObject user = new JSONObject();
		 user.put("username", username);
		 user.put("password", password);
		 return user.toString();
		
	}
	@Before
	public void testSetup() throws Exception{
		Role r1 = new Role("recruit");
		Role r2 = new Role("applicant");
		Role added = roleRepo.save(r1);
		roleRepo.save(r2);
		System.out.println(added);
		String body = setupBody("testyy","testaryy","testay@gmail.com","9443528491","heja","då");
		this.mvc.perform(post("/register")
				.contentType(MediaType.APPLICATION_JSON).content(body.toString()));
	}
	@After
	public void testDestructor() {
		personRepo.deleteById(personRepo.findByUsername("heja").getId());
		roleRepo.deleteAll();
		
	}
	private String setupBody(String firstName, String lastName, String email, String ssn, String username, String password) throws JSONException {
		 JSONObject body = new JSONObject();
			body.put("firstName", firstName);
			body.put("lastName", lastName);
			body.put("email", email);
			body.put("ssn", ssn);
			body.put("username", username);
			body.put("password", password);
			return body.toString();
	}
}
