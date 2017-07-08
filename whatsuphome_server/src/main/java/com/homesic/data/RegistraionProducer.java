package com.homesic.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.homesic.model.Role;
import com.homesic.model.User;

@ViewScoped
public class RegistraionProducer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private enum Mode {
		EDIT, ADD;
	}

	private User user;
	private Mode mode;

	@Produces
	@Named
	public User getSelectedUser() {
		return user;
	}

	@Produces
	@Named
	public void setSelectedUser(User user) {
		
		this.user = user;
	}

	public boolean isAddMode() {
		
		return mode == Mode.ADD;
	}
	
	@PostConstruct
	public void newUser(){
		user = new User();
	}
	
	public void prepareAddUser() {
		
		Role role = new Role();
		role.setRole("user");
		user.getRoles().add(role);
		this.mode = Mode.ADD;
	}

	public void prepareAddOrganizer() {

		Role rolleOrganizer = new Role();
		rolleOrganizer.setRole("admin");
		Role rolleUser = new Role();
		rolleUser.setRole("user");
		user.getRoles().add(rolleOrganizer);
		user.getRoles().add(rolleUser);
		this.mode = Mode.ADD;
	}

	public void prepareEditUser(User user) {
		this.user = user;
		this.mode = Mode.EDIT;
	}

}
