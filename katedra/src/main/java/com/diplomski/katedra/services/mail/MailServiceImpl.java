package com.diplomski.katedra.services.mail;

import org.apache.log4j.Logger;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Created by Andrija on 4/25/2015.
 */
public class MailServiceImpl implements MailService {
    private static final Logger logger = Logger.getLogger(MailServiceImpl.class);

    @Override
    public void sendMail(String to) {
        logger.debug("sendMail");

        String from = "andrija.ilic87@gmail.com";

        String host = "localhost";

        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(properties);

        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            // Send message
            Transport.send(message);
            logger.debug("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
