package se.kth.iv1201.recruitmentbackend.presentation;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.transaction.annotation.Transactional;

import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RecruitmentControllerTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	
	PersonRepository personRepo;
	
	@Test
	public void registerTestBadRequest()throws Exception{
		this.mvc.perform(post("/register")).andDo(print()).andExpect(status().isBadRequest());
	}
		
	@Rollback(true)
	@Test
	public void registerTestWorking()throws Exception{
	
		String body = setupBody("test","testar","testis@gmail.com","9403128991","Testis","då");
			 this.mvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON)
	                .content(body)).andDo(print())
			 		.andExpect(status().isCreated());
			 
		
	}
	
	@Test 
	public void registerAlredyUsedUsername() throws Exception{
		String body = setupBody("testa","testars","testayssss@gmail.com","9494256712","heja","då");
		 registerRequest("A person with the given username already exists!", body);
		 removeUsers();
	}
	@Test 
	public void registerAlredyUsedEmail() throws Exception{
	
		String body = setupBody("testar","testars","testay@gmail.com","9563258789","hejaaa","då");
		 registerRequest("A person with the given email already exists!", body);
		 removeUsers();
	}
	@Test 
	public void registerAlredyUsedSsn() throws Exception{
		
		String body = setupBody("testa","test","finnsej@gmail.com","9443528491","hejaaar","då");
		 registerRequest("A person with the given ssn already exists!", body);
		 removeUsers();
	}
	@Before
	public void testSetup() throws Exception{
		
		String body = setupBody("testyy","testaryy","testay@gmail.com","9443528491","heja","då");
		this.mvc.perform(post("/register")
				.contentType(MediaType.APPLICATION_JSON).content(body.toString()));
	}
	@After
	public void removeUsers() {
		personRepo.deleteAll();
	}
	private void registerRequest(String msg, String body) throws Exception{
		this.mvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON)
                .content(body.toString())).andDo(print())
                .andExpect(status().isMethodNotAllowed())
                .andExpect(jsonPath("$.message").value(msg));
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
