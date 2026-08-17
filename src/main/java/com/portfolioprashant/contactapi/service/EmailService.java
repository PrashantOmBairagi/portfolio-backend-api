package com.portfolioprashant.contactapi.service;

import com.portfolioprashant.contactapi.entity.Contact;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final String TO_ADDRESS   = "hello.prashantbairagi@gmail.com";
    private static final String FROM_ADDRESS = "prashantbairagi.dev@gmail.com";

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends a formatted email to the portfolio owner whenever
     * someone submits the contact form on the frontend.
     *
     * @param contact the validated contact form payload
     */
    public void sendContactEmail(Contact contact) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM_ADDRESS);
        message.setTo(TO_ADDRESS);
        message.setSubject("New Contact from Portfolio - " + contact.getName());
        message.setText(buildEmailBody(contact));
        mailSender.send(message);
    }

    private String buildEmailBody(Contact contact) {
        return String.format(
            "You received a new message from your portfolio contact form.\n\n" +
            "------------------------------\n" +
            "  Name    : %s\n" +
            "  Email   : %s\n" +
            "  Phone   : %s\n" +
            "------------------------------\n\n" +
            "Message:\n%s\n\n" +
            "------------------------------\n" +
            "Sent via contactApi - prashant-bairagi-portfolio.vercel.app",
            contact.getName(),
            contact.getEmail(),
            (contact.getPhone() == null || contact.getPhone().isBlank()) ? "Not provided" : contact.getPhone(),
            contact.getMessage()
        );
    }
}