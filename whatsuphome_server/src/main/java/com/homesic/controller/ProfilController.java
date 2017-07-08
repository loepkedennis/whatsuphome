package com.homesic.controller;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;

import com.homesic.model.User;
import com.homesic.service.interfaces.UserService;
import com.homesic.utils.TestQualifier.MyService;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

@ViewScoped
@Named
public class ProfilController implements Serializable {
	
	/**
	 * 
	 */
	
	
	@Inject
	Logger logger;
	
	private static final long serialVersionUID = 1L;
	
	
	private String password;
	private String repassword;
	
	private String email;
	private String reemail;
	

	
	private boolean isEditing;
	
	
	@Inject
	@MyService
	private UserService userService;
	
	private User user;

	
	@PostConstruct
	public void init(){
		logger.info("### init profilcontroller");
		user = userService.getLoginUser();
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public void doReplacePassword(){
		if((getPassword().equals(getRepassword())) && getPassword().length() >= 6){
			user.setPassword(getPassword());
			userService.updateUser(user);
			toggleEditing();
		}
		else{
			logger.info("### Passwort stimmt nicht überein");
			
		}
		
	}
	
	public void doReplaceAddress(){
		userService.updateUser(user);
	}
	
	public void doReplaceEmail(){
		
		if((getEmail().equals(getReemail())) && getEmail().contains("@")){
			user.setEmail(getEmail());
			userService.updateUser(user);
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/logout.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			toggleEditing();
		}
		else{
			logger.info("### Email stimmt nicht überein");
		}
		
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
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

	public boolean isEditing() {
		return isEditing;
	}

	public void setEditing(boolean editing) {
		isEditing = editing;
	}

	
	public void toggleEditing() {
		isEditing = !isEditing;
	}
	
	
	
	
}
