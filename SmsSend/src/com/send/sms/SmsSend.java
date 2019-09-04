package com.send.sms;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SmsSend {

	public static void main(String[] args) {
		SmsSend s=new SmsSend();
		s.sendSMS();

	}
	
	public void sendSMS(){
		
		String host="smtp.gmail.com";
		String user="mandal.cse43@gmail.com";
		String from =user;
		String to="918861420424@control.txtlocal.co.uk";
		String pss="Oxizen@123";
		String subject ="Message Send by Java Program";
		String message="Hi This is Kartik Mandal";
		boolean sessionDebug=false;
		try{
		Properties props=System.getProperties();
		props.put("mail.smtp.starttls.enable", "tue");
		props.put("mail.smtp.host", "host");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.required", "true");
		//java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.provider());
		
		Session mailSession=Session.getDefaultInstance(props,null);
		mailSession.setDebug(sessionDebug);
		Message msg=new MimeMessage(mailSession);
		msg.setFrom(new InternetAddress(from));
		InternetAddress [] address={new InternetAddress(to)};
		msg.setRecipients(Message.RecipientType.TO, address);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setText(message);
		Transport transport=mailSession.getTransport("smtp");
		transport.connect(host, user, pss);
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
		System.out.println("Done");
		}catch(Exception e){
			System.out.println("Fail");
		}
	}

}
