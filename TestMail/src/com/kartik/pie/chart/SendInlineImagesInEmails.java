package com.kartik.pie.chart;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendInlineImagesInEmails {
 
 public static void sendMail(String data){
   // Recipient's email ID needs to be mentioned.
       String to = "kmandal@yodlee.com";

        // Sender's email ID needs to be mentioned
       String from = "kmandal@yodlee.com";
       final String username = "kmandal";//change accordingly
       final String password = "Mocha@123";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
       String host = "192.168.211.175";

        Properties props = new Properties();
       props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.starttls.enable", "true");
       props.put("mail.smtp.host", host);
       props.put("mail.smtp.port", "25");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
             protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
             }
          });
  
  try {

      
          
          MimeMessage message = new MimeMessage(session); 
          message.setFrom(new InternetAddress(from)); 
          message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 
          message.setSubject("DDDD"); 
          //message.setText(body); 
          Multipart multipart = new MimeMultipart("related");
            
          // first part  (the html)  
          BodyPart messageBodyPart = new MimeBodyPart();  
        //  String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";  
          messageBodyPart.setContent(data, "text/html");  
          messageBodyPart.setDisposition(BodyPart.INLINE);
          // add it  
          multipart.addBodyPart(messageBodyPart);  

          // second part (the image)  
          messageBodyPart = new MimeBodyPart();  
          
          File emailImage = new File("d:\\chemical\\chart.png"); 
          System.out.println("emailImage"+emailImage);

          DataSource fds = new FileDataSource(emailImage);  
          //System.out.println("fds"+fds.getName());
          messageBodyPart.setDataHandler(new DataHandler(fds));  
          messageBodyPart.setHeader("Content-ID","<image>");  

          // add it  
          multipart.addBodyPart(messageBodyPart);  

          // put everything together  
          message.setContent(multipart);  
          Transport.send(message);

           System.out.println("Sent message successfully....");

        } catch (Exception e) {
        e.printStackTrace();
          throw new RuntimeException(e);
       }
 }
 
   public static void main(String[] args) {
     
	   StringBuffer br=new StringBuffer();
	   br.append("<html>\n<head></head>\n<body bgcolor=");
	   br.append('"');
	   br.append("#FFFFFF");
	   br.append('"');
	   br.append(' ');
	   br.append("link=");
	   br.append('"');
	   br.append("#CC6600");
	   br.append('"');
	   br.append(' ');
	   br.append("vlink=");
	   br.append('"');
	   br.append("#CC9900");
	   br.append('"');
	   br.append(' ');
	   br.append("alink=");
	   br.append('"');
	   br.append("#CC9900");
	   br.append('"');
	   br.append('>');
	   br.append("<img height=");
	   br.append('"');
	   br.append("200");
	   br.append('"');
	   br.append(' ');
	   br.append("width=");
	   br.append('"');
	   br.append("200");
	   br.append('"');
	   br.append(' ');
	   br.append("style=");
	   br.append('"');
	   br.append("display: block; margin-left: auto; margin-right: auto;");
	   br.append('"');
	   br.append(' ');
	   br.append("src=");
	   br.append('"');
	   br.append("cid:image");
	   br.append('"');
	   br.append('/');
	   br.append('>');
	   br.append("</body>\n</html>\n");
	   
	   String data ="<H1>Hello</H1><img src=\"cid:image\">";

   sendMail(data);
  
   }
}
