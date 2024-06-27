package com.niit.emailSender.service;

import com.niit.emailSender.config.MovieDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import java.util.Random;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    private JavaMailSender mailSender;

    @Autowired
    public EmailSenderServiceImpl(JavaMailSender javaMailSender) {
        this.mailSender = javaMailSender;
    }
    @RabbitListener(queues = "EmailQueue")
    @Override
    public void sendEmail(MovieDTO movieDTO) {
      String to = movieDTO.getJsonObject().get("to").toString();
      String subject = movieDTO.getJsonObject().get("subject").toString();
      String message = movieDTO.getJsonObject().get("message").toString();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("movieplexthecinema@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        mailSender.send(simpleMailMessage);
        System.out.println("Success");
    }
    @Override
    public void sendEmail1(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("movieplexthecinema@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        mailSender.send(simpleMailMessage);
        System.out.println("Success");
    }

    private static int randomNumber(){
        Random random = new Random();
        int otp = random.nextInt(4000)+1000;
        return otp;
    }
    @Override
    public int sendOtp(String name,String email){
        System.out.println("----email-service---" +name + email);
        int   otp = randomNumber();
        String emailContent = "Dear "+name+",\n" +
                "\n" +
                "Please find below your One-Time Password (OTP) for account verification:\n" +
                "\n" +
                "OTP: "+otp+"\n" +
                "\n" +
                "Kindly enter this OTP within the designated timeframe to complete the verification process.\n" +
                "\n" +
                "Best regards,\n" +
                "Movieplex Team";
        String subject ="Regarding forgot password.";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setText(emailContent);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setFrom("movieplexthecinema@gmail.com");
        simpleMailMessage.setTo(email);
        mailSender.send(simpleMailMessage);
        return  otp;
    }
}
