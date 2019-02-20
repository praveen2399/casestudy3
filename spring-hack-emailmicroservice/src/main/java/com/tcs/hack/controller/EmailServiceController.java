package com.tcs.hack.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.hack.service.EmailService;

@RestController
public class EmailServiceController {
	
	@Autowired
	EmailService emailService;
	
	/*@RequestMapping(value="/sendemail/{emailId}")
	public void sendEmail(@PathVariable String emailId)
	{
		try {
			emailService.sendmail(emailId);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}
