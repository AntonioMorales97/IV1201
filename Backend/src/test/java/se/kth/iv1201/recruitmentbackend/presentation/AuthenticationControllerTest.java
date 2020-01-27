package se.kth.iv1201.recruitmentbackend.presentation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthenticationControllerTest {
	@Autowired
	private MockMvc mvc;

	/**
	 * Test that should fail.
	 * @throws Exception
	 */
	@Test
	public void registerTestBadRequest()throws Exception{
		this.mvc.perform(post("/authenticate")).andDo(print()).andExpect(status().isBadRequest());
	}
}
