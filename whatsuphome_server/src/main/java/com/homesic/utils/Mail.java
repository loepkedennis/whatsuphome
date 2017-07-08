package com.homesic.utils;

import java.util.logging.Level;
import java.util.logging.Logger;


import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Session;

import javax.mail.Message;
import javax.mail.MessagingException;
 
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;





@Stateless
public class Mail {
 
    @Resource(mappedName = "java:jboss/mail/test")
    private Session session;
 
    public void send(String addresses, String topic, String textMessage, String email) {
    	
        try {
 
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
            message.setFrom(new InternetAddress(email));
            message.setSubject(topic);
            message.setText(textMessage);
 
            Transport.send(message);
 
        } catch (MessagingException e) {
            Logger.getLogger(Mail.class.getName()).log(Level.WARNING, "Cannot send mail", e);
        }
    }
}