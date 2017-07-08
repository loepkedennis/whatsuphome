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
public class NavigationController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	
	@Inject
	private DeviceController deviceController;
	
	@Inject
	@MyService
	private UserService userService;
	
	@Inject
	Logger logger;
	
	@PostConstruct
	public void init(){
		user = userService.getLoginUser();
	}

	public void goLogout() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/LogoutServlet");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	public String goHome(){
//		if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("admin")){
//			return Pages.INDEX;
//		}
//		else{
			return Pages.INDEX;
//		}
	}

	public String goBack() {
		return "/index";
	}
	
	public String goDevices(){
		deviceController.init();
		return Pages.DEVICE;
	}
	
	public String goKonto(){
		return Pages.KONTO;
	}
	
	public String goUser(){
		return Pages.USER;
	}
	
	public String goSystem(){
		return Pages.SYSTEM;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
