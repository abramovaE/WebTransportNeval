package com.springapp.mvc.emailWorker;

import org.apache.log4j.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by kot on 24.08.17.
 */
public class SendEMail {

    private static final Logger logger = Logger.getLogger(SendEMail.class.getName());


    public static void sendMailWithAttachment(final String username, final String password,
                                              final String recipients, String mes, String fileName) throws MessagingException{

        logger.info("Начинаем отправку письма");
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // create a message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
            message.setSubject("Транспортный отчет", "utf-8");


            // create and fill the first message part
            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText(mes, "utf-8");

            // create the second message part
            MimeBodyPart mbp2 = new MimeBodyPart();

            // attach the file to the message
            FileDataSource fds = new FileDataSource(fileName);
            mbp2.setDataHandler(new DataHandler(fds));
            mbp2.setFileName(MimeUtility.encodeText(fds.getName()));

            // create the Multipart and add its parts to it
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mbp1);
            mp.addBodyPart(mbp2);

            // add the Multipart to the message
            message.setContent(mp, "utf-8");

            // set the Date: header
            message.setSentDate(new Date());

            // send the message
            Transport.send(message);
            logger.info("Письмо успешно отправлено");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    public static void sendMailWithoutAttachment(final String username, final String password,
                                                 final String recipients, com.springapp.mvc.model.Message mess, String senderName) throws MessagingException {


        logger.info("Начинаем отправку письма");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

            // create a message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
            message.setSubject(mess.getSubject() + " от " + senderName);




            // create and fill the first message part
            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText(mess.getText());

            // create the Multipart and add its parts to it
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mbp1);

            // add the Multipart to the message
            message.setContent(mp, "utf-8");

            // set the Date: header
            message.setSentDate(new Date());

            // send the message
            Transport.send(message);
            logger.info("Письмо успешно отправлено");
    }
}
