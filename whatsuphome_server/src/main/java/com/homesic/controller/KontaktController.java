package com.homesic.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.homesic.model.User;
import com.homesic.service.interfaces.UserService;
import com.homesic.utils.Caputre;
import com.homesic.utils.Mail;
import com.homesic.utils.TestQualifier.MyService;

import java.io.Serializable;

@ViewScoped
@Named
public class KontaktController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Logger logger = Logger.getLogger(KontaktController.class.getName());
	
	@EJB
	private Mail mail;

	private String email;
	
	private String reemail;
	
	private String emailAdresse;

	private String emailTitel;
	
	@Inject
	private Caputre capture;
	
	@Inject
	@MyService
	private UserService userService;
	

	public String sendMail() {
		String userpassword = " ";
		if(capture.vergleich()){
			logger.log(Level.INFO, "### Caputre ist gültig");
		}
		else{
			logger.log(Level.WARNING, "### Caputre ist ungültig");
			
		}
		
		if(email.equals(reemail)){
			User  oldUser = userService.userAvailable(email);
			
			if(oldUser != null){
				userpassword = oldUser.getPassword();
				if (!email.isEmpty() && !reemail.isEmpty() && !userpassword.isEmpty()) {

					emailAdresse = getEmail();
					emailTitel = "Neues Password";
					
					StringBuilder message = new StringBuilder();

					message.append("Mit dieser eMail erhalten Sie ihr Password! ");
					message.append("\nEmail:  ");
					message.append(email);
					message.append("\nPassword: "+userpassword);

					logger.log(Level.INFO,"### Es wurde ein Konkatformular versendet");
					
					mail.send(emailAdresse, emailTitel, message.toString(), "info@loepke-it.de");
					clearFeld();
				}
				else{
					logger.log(Level.WARNING, "### Ein leeres Kontaktformular wurde abgeschickt");
				}
				
			}
	
		}
		else{
			logger.log(Level.WARNING, "### Kein User wurde gefunden");
		}
		
		
		return "mailsuccess";

	}
	
	private void clearFeld(){
		email = "";
		reemail = "";
		capture.setCaptureWertVergleich("");
	}
	
	public Caputre getCapture() {
		return capture;
	}

	public void setCapture(Caputre capture) {
		this.capture = capture;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReemail() {
		return reemail;
	}

	public void setReemail(String reemail) {
		this.reemail = reemail;
	}

	

}
