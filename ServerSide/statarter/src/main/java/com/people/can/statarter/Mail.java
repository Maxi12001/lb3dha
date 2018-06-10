package com.people.can.statarter;
import javax.mail.*;
import java.util.*;
import javax.mail.internet.*;

public class Mail  implements Runnable{

    private  String USER_NAME = "****";  // GMail user name (just the part before "@gmail.com")
    private  String PASSWORD = "*****"; // GMail password
    private  String RECIPIENT = "";
    private String Code;
    public void setRecip(String r,String code){
    	RECIPIENT=r;
    	Code =code;
    }
    public void send() {
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { RECIPIENT }; // list of recipient email addresses
        String subject = "أهلا بك في لبعضها ";
        String body = ":أهلا بك في في تطبيق لبعضها لإتمام عملية التسجيل برجاء ادخال الكود التالي ";
        body=body+"\n"+Code;
        sendFromGMail(from, pass, to, subject, body);
    }

    private void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session =Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
	@Override
	public  void run() {
		send();
		
	}
}
