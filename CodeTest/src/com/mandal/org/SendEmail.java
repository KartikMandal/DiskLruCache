package com.mandal.org;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
  
public class SendEmail  
{  
 public static void main(String [] args){  
      String to = "kmandal@yodlee.com";//change accordingly  
      String from = "snandi3@yodlee.com";//change accordingly 
      String cc = null;//change accordingly
      String fromAlias="SPS_Dev";
  
     //Get the session object  
      Properties properties = System.getProperties();  
      properties.setProperty("mail.smtp.host", "10.79.2.15");
      properties.setProperty("mail.smtp.port", "25");
      Session session = Session.getInstance(properties, null);
  
     //compose the message  
      MimeMessage msg = new MimeMessage(session);

		try{
			//set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");  
			try {
				msg.setFrom(new InternetAddress(from, fromAlias));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			msg.setReplyTo(InternetAddress.parse(from, false));
			msg.setSubject("Ping", "UTF-8");
			msg.setText("Hello, this is example of sending email ", "UTF-8");
			msg.setSentDate(new Date());
			if(to !=null){
			msg.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
			//msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			}
			/*if(cc !=null){
			msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc, false));
			}*/
  
         // Send message  
         Transport.send(msg);  
         System.out.println("message sent successfully....");  
  
      }catch (MessagingException mex) {mex.printStackTrace();}  
   }  
}  
