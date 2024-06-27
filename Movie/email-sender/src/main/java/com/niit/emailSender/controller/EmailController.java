package com.niit.emailSender.controller;

import com.niit.emailSender.domain.EmailMessage;
import com.niit.emailSender.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;

@RestController
@RequestMapping("/api/v2/sendEmail")
public class EmailController {
    private  EmailSenderService emailSenderService;
    @Autowired
    public EmailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/send-email")
    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage) throws AddressException {
       this.emailSenderService.sendEmail1(emailMessage.getTo(),emailMessage.getSubject(),emailMessage.getMessage());
       return  ResponseEntity.ok("Successfully send");
    }

    @GetMapping("/forgot-password/{name}/{email}")
    public ResponseEntity sendOtp(@PathVariable String name,@PathVariable String email ){
        System.out.println("--------------email-sender-controller-----------" +name + email);
        return new ResponseEntity(emailSenderService.sendOtp(name,email), HttpStatus.OK);
    }
}
