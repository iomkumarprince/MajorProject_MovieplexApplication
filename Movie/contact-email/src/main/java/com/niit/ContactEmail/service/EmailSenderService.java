package com.niit.ContactEmail.service;



public interface EmailSenderService {

    public void sendEmail(String email, String name);
    public void sendEmailToTeam(String name, String message);
}
