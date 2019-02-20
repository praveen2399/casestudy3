package com.tcs.hack.service;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.tcs.hack.model.OrderDTO;

@Service
public class EmailService {
	
	@Value("${com.tcs.hack.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${com.tcs.hack.rabbitmq.routingkey}")
	private String routingkey;
	


	
	public void sendmail(String emailId,Long orderID,String orderStatus) throws AddressException, MessagingException, IOException {
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("pk2399user@gmail.com", "1234pk2399");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("pk2399user@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailId));
		   msg.setSubject("Order Created "+orderID);
		   msg.setContent("Order Created", "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("Your order created and with status: "+orderStatus, "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		  
		   msg.setContent(multipart);
		   Transport.send(msg);
		}
	
	
	@RabbitListener(queues="${com.tcs.hack.rabbitmq.queue}")
	public void recieveEmail(OrderDTO order) throws AddressException, MessagingException, IOException
	{
		System.out.println("Recieved Message From RabbitMQ in Email: "+order.getCustomerID());
		
		sendmail("pk2399user@gmail.com",order.getOrderId(),order.getOrderStatus());
	}
	
}
