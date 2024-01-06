package org.jsp.mailsendingapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@RestController
public class SendMailController {
	@Autowired
	private JavaMailSender mailSender;

	@PostMapping("/send")
	public String sendEmail(@RequestParam String email) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(email);
			helper.setText("We are sending this Email to check my Mail Sending API...:)");
			helper.setSubject("Testing API");
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println(mailSender);
		return "Mail sent to:" + email;
	}
}
