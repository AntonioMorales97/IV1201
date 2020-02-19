package se.kth.iv1201.recruitmentbackend.presentation;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import se.kth.iv1201.recruitmentbackend.domain.Application;
import se.kth.iv1201.recruitmentbackend.domain.Person;
import se.kth.iv1201.recruitmentbackend.domain.Role;
import se.kth.iv1201.recruitmentbackend.jwt.JwtTokenUtil;
import se.kth.iv1201.recruitmentbackend.repository.ApplicationRepository;
import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;
import se.kth.iv1201.recruitmentbackend.repository.RoleRepository;
import se.kth.iv1201.recruitmentbackend.repository.StatusRepository;
import se.kth.iv1201.recruitmentbackend.security.MyUserDetailsService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationControllerTest {

	@Autowired
	ApplicationRepository applicationRepo;

	@Autowired
	StatusRepository statusRepo;

	@Autowired
	PersonRepository personRepo;

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	private MockMvc mvc;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	JwtTokenUtil jwtUtil;

	private Person dummyPerson;

	private Application dummyApplication;

	/**
	 * Sets up a dummy data for an Application
	 */
	@Before
	public void construct() {
		applicationRepo.deleteAll();
		personRepo.deleteAll();
		roleRepo.deleteAll();
		Role r1 = new Role("recruit");
		Role r2 = new Role("applicant");
		roleRepo.save(r1);
		roleRepo.save(r2);
		dummyPerson = new Person("testyy", "testaryy", "applicationControll@gmail.com", "9443898491",
				"applicationControll", "då", roleRepo.findByName("recruit"));

		personRepo.save(dummyPerson);

		dummyApplication = new Application(statusRepo.findByName("unhandled").get(),
				personRepo.findByUsername("applicationControll"));
		applicationRepo.save(dummyApplication);
	}

	@Test
	public void getApplicationsTestBadRequest() throws Exception {
		this.mvc.perform(get("/applications")).andDo(print()).andExpect(status().isForbidden());
	}

	@Test
	public void getApplicationsTest() throws Exception {
		String jwt = setupJWT();

		MvcResult res = this.mvc.perform(
				get("/applications").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + jwt))
				.andDo(print()).andExpect(status().isOk()).andReturn();
		String result = res.getResponse().getContentAsString();
		String response = result.substring(15, 15 + 24);

		assertEquals("applicationListResponses", response);
	}

	@Test
	public void getApplicationTest() throws Exception {
		String jwt = setupJWT();
		List<Application> applicationlist = applicationRepo.findAll();
		String id = Long.toString(applicationlist.get(0).getId());

		this.mvc.perform(get("/application/" + id).contentType(MediaType.APPLICATION_JSON).header("Authorization",
				"Bearer " + jwt)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void getApplicationTestFail() throws Exception {
		String jwt = setupJWT();

		this.mvc.perform(get("/application/420").contentType(MediaType.APPLICATION_JSON).header("Authorization",
				"Bearer " + jwt)).andDo(print()).andExpect(status().is4xxClientError());
	}

	@Test
	public void changeStatusTest() throws Exception {
		String jwt = setupJWT();
		List<Application> applicationlist = applicationRepo.findAll();
		String id = Long.toString(applicationlist.get(0).getId());
		System.out.println("\n ID: " + id + "\n");
		JSONObject statusDTO = new JSONObject();
		statusDTO.put("name", "rejected");
		statusDTO.put("version", applicationlist.get(0).getVersion());
		this.mvc.perform(put("/alter-status/" + id).contentType(MediaType.APPLICATION_JSON)
				.content(statusDTO.toString()).header("Authorization", "Bearer " + jwt)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void changeStatusTestFail() throws Exception {
		String jwt = setupJWT();
		this.mvc.perform(put("/alter-status/420").contentType(MediaType.APPLICATION_JSON).header("Authorization",
				"Bearer " + jwt)).andDo(print()).andExpect(status().is4xxClientError());
	}

	private String setupJWT() throws Exception {
		UserDetails userDetails = userDetailsService.loadUserByUsername(dummyPerson.getUsername());
		System.out.println(userDetails.getUsername());
		return jwtUtil.createToken(userDetails);

	}

	@After
	public void deStruct() {
		applicationRepo.deleteAll();
		personRepo.deleteAll();
		roleRepo.deleteAll();
	}
}
