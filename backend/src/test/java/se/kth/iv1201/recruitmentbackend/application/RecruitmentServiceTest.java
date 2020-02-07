/*package se.kth.iv1201.recruitmentbackend.application;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import se.kth.iv1201.recruitmentbackend.application.exception.IllegalTransactionException;
import se.kth.iv1201.recruitmentbackend.presentation.dto.PersonDTO;
import se.kth.iv1201.recruitmentbackend.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RecruitmentServiceTest {
@Autowired
RecruitmentService recruitmentService;
@Autowired
PersonRepository personRepo;

@Before
public void setupUser() {
	register("taget", "taget","tagen@gmail.com", "9501039482", "tagen", "Detkanske");
}

@Test
public void registrationTest() throws Exception{
	
	final long  countBefore = personRepo.count();
	System.out.println("\n"+countBefore+"\n");
	register("funkar", "Det","Här@gmail.com", "9403021583", "Kanske", "Detkanske");
	final long countAfter = personRepo.count();
	System.out.println("\n"+countAfter+"\n");
	assertGreaterThan(countAfter,countBefore);	
}
@Test(expected= IllegalTransactionException.class)
public void registrationUsernameExistTest() throws Exception{
	register("funkar", "Det","då@gmail.com", "9403521583", "tagen", "Detkanske");
	
}
@Test(expected= IllegalTransactionException.class)
public void registrationEmailExistTest() throws Exception{
	register("funkar", "Det","tagen@gmail.com", "9403521583", "dålba", "Detkanske");
	
}
@Test(expected= IllegalTransactionException.class)
public void registrationSsnExistTest() throws Exception{
	register("funkar", "Det","dåbla@gmail.com", "9501039482", "dåäka", "Detkanske");
	
}
@After
public void destructUser() {
	deleteUser("tagen");
	deleteUser("Kanske");
	
}
public static void assertGreaterThan(long countAfter, long countBefore) {
    assertGreaterThan(countAfter, countBefore, null);
}

public static void assertGreaterThan(long greater, long lesser, String message) {
    if (greater <= lesser) {
        fail((StringUtils.isNotBlank(message) ? message + " ==> " : "") +
                "Expected: a value greater than <" + lesser + ">\n" +
                "But <" + greater + "> was " + (greater == lesser ? "equal to" : "less than") + " <" + lesser + ">");
    }
}
private void deleteUser(String username) {
	personRepo.deleteById(personRepo.findByUsername(username).getId());
}
private void register(String firstname, String lastname, String email, String ssn, String username, String password) {
	PersonDTO workingPerson = new PersonDTO(firstname, lastname,email, ssn, username, password);
	recruitmentService.registerUser(workingPerson);
}
}
*/