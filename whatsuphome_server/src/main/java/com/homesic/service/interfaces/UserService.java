package com.homesic.service.interfaces;


import java.util.List;

import com.homesic.model.User;



public interface UserService {
	
	void addUser(User user);
	
	void deleteUser(User user);
	
	void updateUser(User user);
	
	User getLoginUser();
	
	List<User> getOrganizer();
	
	User userAvailable(String eMail);
	
}
