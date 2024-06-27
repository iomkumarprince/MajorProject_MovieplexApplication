package com.niit.ContactEmail.controller;


import com.niit.ContactEmail.domain.EmailMessage;
import com.niit.ContactEmail.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/contactEmail")
public class EmailController {
    private EmailSenderService emailSenderService;
    @Autowired
    public EmailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/contact-email")
    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage) {
        this.emailSenderService.sendEmailToTeam(emailMessage.getName(),emailMessage.getText());
       this.emailSenderService.sendEmail(emailMessage.getEmail(),emailMessage.getName());
       return new ResponseEntity<>(HttpStatus.OK);
    }
}
