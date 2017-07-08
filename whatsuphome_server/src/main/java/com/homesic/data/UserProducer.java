package com.homesic.data;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;

import javax.inject.Inject;

import com.homesic.model.User;
import com.homesic.service.interfaces.UserService;
import com.homesic.utils.Events.Added;
import com.homesic.utils.Events.Deleted;
import com.homesic.utils.Events.Updated;
import com.homesic.utils.TestQualifier.MyService;



@RequestScoped
public class UserProducer {
	
	 
		@Inject
		@MyService
		private UserService userSevice;

		public void onUserAdded(@Observes @Added User user){
			userSevice.addUser(user);
		}
		
		public void onUserDeleted(@Observes @Deleted User user){
			userSevice.deleteUser(user);
			
		}
		
		public void onUserUpdated(@Observes @Updated User user){
			userSevice.updateUser(user);
			
		}
		
		

}
