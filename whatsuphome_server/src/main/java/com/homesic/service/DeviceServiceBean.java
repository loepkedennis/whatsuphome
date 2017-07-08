package com.homesic.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.homesic.model.Device;
import com.homesic.model.User;
import com.homesic.service.interfaces.DeviceService;
import com.homesic.utils.TestQualifier.MyService;


@Stateless
@MyService
public class DeviceServiceBean implements DeviceService {

	@Inject
	Logger logger;
	
	@Inject
	EntityManager entityManger;
	
	@Override
	public void addDevice(Device device) {
		
		entityManger.persist(device);
		entityManger.flush();
		
	}
	
	
	public boolean findDevice(String deviceId){
		Device device = null;
		try{
			device = entityManger.createNamedQuery(Device.findDeviceId, Device.class).setParameter("token", deviceId).getSingleResult();
		}
			catch(NoResultException ex){
				logger.warning("### Keinen User gefunden: "+ex.getStackTrace());
			}
		
		
			if(device != null){
				logger.info("### Device found");
				return true;
			}
			
		
		return false;
	}
	
	public Device getUpdateDevice(Device device){
		Device managedDevice = entityManger.find(Device.class, device.getId());
		return managedDevice;
		
	}
	
	public void updateData(String deviceId, String temperature, String humidity, String sensor){
		Device device = null;
		try{
			device = entityManger.createNamedQuery(Device.findDeviceId, Device.class).setParameter("token", deviceId).getSingleResult();
		}
			catch(NoResultException ex){
				logger.warning("### Keinen User gefunden: "+ex.getStackTrace());
			}
		
		if(device != null){
			device.setTemperatur(temperature);
			device.setHumidity(humidity);
			if(sensor.equals("on")){
				device.setSensor(true);
			}
			else if(sensor.equals("off")){
				device.setSensor(false);
			}
			
			updateDevice(device);
		}
		
		
	}
	
	public void addDeviceWithUser(Device device, User user) {
		
		User mergeUser = entityManger.merge(user);
		
		device.getUser().add(mergeUser);
		
		entityManger.persist(device);
		entityManger.flush();
		
	}
	
	
	public List<Device> getAllDevicess() {
		
		TypedQuery<Device> query = 
			entityManger.createNamedQuery(Device.findAll,Device.class);
		List<Device> devices = query.getResultList();
		logger.info("### DeviceList Size: "+devices.size());
		
		return devices;
	}



	@Override
	public void deleteDevice(Device device) {
		// TODO Auto-generated method stub
		Device managedDevice = entityManger.find(Device.class, device.getId());
		entityManger.remove(managedDevice);
		
	}

	@Override
	public void updateDevice(Device device) {
		// TODO Auto-generated method stub
		entityManger.merge(device);
		
	}

}
