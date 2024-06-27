package com.niit.ContactEmail.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    private JavaMailSender mailSender;
    public static String ticketNo ="";

    @Autowired
    public EmailSenderServiceImpl(JavaMailSender javaMailSender) {
        this.mailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String email, String name) {
        String subject ="Ticket Confirmation #"+ticketNo+" - Movieplex App Inquiry";

        String message="Dear "+name+",\n" +
                "\n" +
                "Thank you for contacting Movieplex Support. This email confirms that we have received your ticket regarding the issue with our Movieplex app. Our team is actively working on resolving it.\n" +
                "\n" +
                "Ticket Details:\n" +
                "Ticket Number: #"+ticketNo+"\n" +
                "\n" +
                "We appreciate your patience, and we assure you that we will provide a prompt resolution. If we require any additional information, we will reach out to you.\n" +
                "\n" +
                "Please rest assured that we are committed to addressing your concern and getting you back to enjoying our Movieplex app smoothly.\n" +
                "\n" +
                "Thank you for choosing Movieplex. We will keep you updated on the progress of your ticket.\n" +
                "\n" +
                "Best regards,\n" +
                "\n" +
                "Movieplex Support Team";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("movieplexthecinema@gmail.com");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        mailSender.send(simpleMailMessage);
        System.out.println("Success");
    }
    private static String randomNumber(){
        Random random = new Random();
        int ticketNo = random.nextInt(9000)+1000;
        return "RQ"+ticketNo;
    }
    @Override
    public void sendEmailToTeam(String name,String message) {
        ticketNo = randomNumber();
        String emailContent = "Dear Developer Team,\n\n" +
                "I hope this email finds you well. I wanted to inform you that we have received a new query on the Movieplex website. The details of the query are as follows:\n\n" +
                "Query Details:\n"+
                "Ticket Number: #"+ticketNo+"\n" +
                "Customer Name: "+name+"\n" +
                "The customer has reported an issue."+"\n"+message
                +"\n\n"+
                "Best regards,\n"+
                "Movieplex Support Team";
        String subject ="Ticket No #"+ticketNo+" - Movieplex App Enquiry ";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("movieplexthecinema@gmail.com");
        simpleMailMessage.setTo("kumarprincelkh@gmail.com");
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(emailContent);
        mailSender.send(simpleMailMessage);
        System.out.println("Success");
    }
}
