package com.example.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private String HOST_NAME = "smtp.yandex.ru";
    private int PORT_NAME = 465;
    private String USER_NAME = "gevorkmelikfashyan@yandex.ru";
    private String PASSWORD = "jjibzexueacyfwcq";

    @Async
    public void sendEmail(String mailTo, String theme, String text) {
        String adressee = mailTo;
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST_NAME);
        props.put("mail.smtp.port", PORT_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_NAME, PASSWORD);}});
        try {
            Message message = new MimeMessage(session);
            message.setSubject(theme);
            message.setText(text);
            message.setFrom(new InternetAddress(USER_NAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(adressee));
            Transport.send(message);
            System.out.println("Письмо отправлено");
            logger.info("Email sent successfully to: {}", mailTo);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
