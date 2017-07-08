package com.homesic.service;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.homesic.model.User;
import com.homesic.service.interfaces.UserService;
import com.homesic.utils.TestQualifier.MyService;







@Stateless
@MyService
public class UserServiceBean implements UserService {

	@Inject
	Logger logger;
	
	@Inject
	EntityManager entityManger;

	@Resource
	private SessionContext sessionContext;
	
	
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
	//	RolleBean rolle = new RolleBean();
	//	rolle.setRolle("User");
		//TypedQuery<RolleBean> query = entityManger.createNamedQuery(RolleBean.userRole, RolleBean.class);
		//List<RolleBean> rollen = query.getResultList();
		//logger.info("### RollenListe"+rollen.get(0).getRolle().toString());
		//user.setRollen(rollen);
		entityManger.persist(user);
		//entityManger.persist(rolle);
		entityManger.flush();
	//	user.getRollen().add(rolle);
	//	entityManger.persist(rolle);
	//	entityManger.flush();
		
		
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		User managedUser = entityManger.find(User.class, user.getId());
		entityManger.remove(managedUser);
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		entityManger.merge(user);
		
	}
	
	@Override
	public User getLoginUser(){
		User user = null;
		String userEmail = sessionContext.getCallerPrincipal().getName();
		logger.info("### user "+userEmail);
		if(userEmail.equals("anonymous")){
			return null;
		}
		try{
			 user = entityManger.createNamedQuery(User.findByEmail, User.class).setParameter("email", userEmail).getSingleResult();
			 logger.info("### getUser bei email: "+user.getFirstName());
		}
			catch(NoResultException ex){
				logger.warning("### Keinen User gefunden: "+ex.getStackTrace());
			}
		return user;
		
	}
	
	public List<User> getOrganizer(){
		Query query = entityManger.createQuery("SELECT c FROM User c INNER JOIN c.rollen i WHERE i.rolle = 'Organizer'");
		@SuppressWarnings("unchecked")
		List<User> org = query.getResultList();
		logger.info("#### Org liste "+org.size());
		return org;
	}

	@Override
	public User userAvailable(String eMail) {
		User userAvailable = null;
		try{
			userAvailable = entityManger.createNamedQuery(User.findByEmail, User.class).setParameter("email", eMail).getSingleResult();
			if(userAvailable != null){
				return userAvailable;
			}
		
		}catch(NoResultException ex){
			logger.warning("### Keinen User gefunden: "+ex.getStackTrace());
		}
			return null;
	}

}
