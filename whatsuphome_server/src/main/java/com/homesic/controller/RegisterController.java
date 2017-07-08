package com.homesic.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.homesic.data.RegistraionProducer;
import com.homesic.model.User;
import com.homesic.utils.Events.Added;
import com.homesic.utils.Events.Updated;

@Named
@ViewScoped
public class RegisterController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String registerCode;

	public String getRegisterCode() {
		return registerCode;
	}

	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}

	@Inject
	Logger logger;
	
	@Inject
	@Added
	private Event<User> userAddEvent;

	@Inject
	@Updated
	private Event<User> userUpdateEvent;

	@Inject
	private RegistraionProducer userProducer;

	public String doSave() {
		
		if (getRegisterCode().equals("User")) {
			userProducer.prepareAddUser();
			
		} else if (getRegisterCode().equals("Admin")) {
			userProducer.prepareAddOrganizer();
		}
		
		if(userProducer.isAddMode()){
			String email = userProducer.getSelectedUser().getEmail();
			String reemail = userProducer.getSelectedUser().getReemail();
			String passwort = userProducer.getSelectedUser().getPassword();
			String repasswort = userProducer.getSelectedUser().getRepassword();
			if((email.compareTo(reemail)==0) && (passwort.compareTo(repasswort)==0)){
				userAddEvent.fire(userProducer.getSelectedUser());
			}
			else{
				logger.warning("### Facecontext");
				FacesMessage message = new FacesMessage("Passwort oder Email stimmen nicht überein!");				
				FacesContext.getCurrentInstance().addMessage("test", message);
				
			}
//			try {
//				FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
		}
		else{
			userUpdateEvent.fire(userProducer.getSelectedUser());
		}
		
		
		
		
		

		
//		if (userProducer.isAddMode()) {
//			userAddEvent.fire(userProducer.getSelectedUser());
//		} else {
//			userUpdateEvent.fire(userProducer.getSelectedUser());
//		}
		return Pages.SUCCESS;
	}

	public String doRegister() {

		userProducer.newUser();
		
		return Pages.REGISTER;
	}
	// public String doRegister() {
	// // logger.log(Level.INFO, "###Test erfolgreich");
	// if (getRegisterCode().equals("User")) {
	// userProducer.prepareAddUser();
	// doSave();
	// return Pages.INDEX;
	// } else if (getRegisterCode().equals("Admin")) {
	// userProducer.prepareAddOrganizer();
	// doSave();
	// return Pages.INDEX;
	// }
	// return Pages.LOGOUT;
	// }
}
