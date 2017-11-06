package com.bookshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

  @Autowired
  private JavaMailSender mailSender;

  public void sendMail(String sendTo, String mailSubject, String mailContent) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(sendTo);
    mailMessage.setText(mailContent);
    mailMessage.setSubject(mailSubject);
    mailSender.send(mailMessage);
  }

}
