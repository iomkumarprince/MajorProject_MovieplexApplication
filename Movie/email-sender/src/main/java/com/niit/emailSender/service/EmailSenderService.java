package com.niit.emailSender.service;

import com.niit.emailSender.config.MovieDTO;

import javax.mail.internet.AddressException;

public interface EmailSenderService {
    void sendEmail(MovieDTO movieDTO) throws AddressException;
    public void sendEmail1(String to, String subject, String message) throws AddressException;
    public int sendOtp(String name,String email);
}
