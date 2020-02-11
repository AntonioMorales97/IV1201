package se.kth.iv1201.recruitmentbackend.application;


import static org.junit.Assert.assertEquals;
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
private static PersonDTO dummyPerson;

@Before
public void setupUser() {
	 dummyPerson = new PersonDTO("taget", "taget","tagen@gmail.com", "9501039482", "tagen", "Detkanske");

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
	register(dummyPerson.getFirstName(), dummyPerson.getLastName(),dummyPerson.getEmail(),dummyPerson.getSsn(),dummyPerson.getUsername(),dummyPerson.getPassword());
	register("funkar", "Det","då@gmail.com", "9403521583", "tagen", "Detkanske");
	
}
@Test(expected= IllegalTransactionException.class)
public void registrationEmailExistTest() throws Exception{
	register(dummyPerson.getFirstName(), dummyPerson.getLastName(),dummyPerson.getEmail(),dummyPerson.getSsn(),dummyPerson.getUsername(),dummyPerson.getPassword());
	register("funkar", "Det","tagen@gmail.com", "9403521583", "dålba", "Detkanske");
	
}
@Test(expected= IllegalTransactionException.class)
public void registrationSsnExistTest() throws Exception{
	register(dummyPerson.getFirstName(), dummyPerson.getLastName(),dummyPerson.getEmail(),dummyPerson.getSsn(),dummyPerson.getUsername(),dummyPerson.getPassword());
	register("funkar", "Det","dåbla@gmail.com", "9501039482", "dåäka", "Detkanske");
	
}

/**
 * Helper function to evaluate if first parameter is greater then second.
 * @param x Value one.
 * @param x  Value two.
 */
public static void assertGreaterThan(long x, long y) {
    assertGreaterThan(x, y, null);
}

private static void assertGreaterThan(long greater, long lesser, String message) {
    if (greater <= lesser) {
        fail((StringUtils.isNotBlank(message) ? message + " ==> " : "") +
                "Expected: a value greater than <" + lesser + ">\n" +
                "But <" + greater + "> was " + (greater == lesser ? "equal to" : "less than") + " <" + lesser + ">");
    }
    
}
private void register(String firstname, String lastname, String email, String ssn, String username, String password) throws IllegalTransactionException{
	PersonDTO workingPerson = new PersonDTO(firstname, lastname,email, ssn, username, password);
	recruitmentService.registerUser(workingPerson);
}

/*@After
public void destructUser() {
	deleteUser("tagen");
	deleteUser("Kanske");
	
}
private void deleteUser(String username) {
	personRepo.deleteById(personRepo.findByUsername(username).getId());
}*/
}
