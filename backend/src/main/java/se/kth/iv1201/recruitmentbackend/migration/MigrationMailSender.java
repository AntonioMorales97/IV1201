package se.kth.iv1201.recruitmentbackend.migration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Class used to send e-mails to users with important information
 */
@Component
public class MigrationMailSender {

	@Autowired
	private JavaMailSender sender;

	/**
	 * Sends an email to a user containing their sign-in credentials.
	 * 
	 * @param username is the users username
	 * @param password is the users password.
	 * @param email    is the e-mail address that the message will be sent to.
	 */
	public void sendCredentials(String username, String password, String email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Your new password");
		message.setText("Username: " + username + "\nPassword: " + password);
		sender.send(message);
	}

}
