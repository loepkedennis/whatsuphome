package com.homesic.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


import com.homesic.model.Device;
import com.homesic.model.User;
import com.homesic.service.interfaces.DeviceService;
import com.homesic.service.interfaces.UserService;

import com.homesic.utils.TestQualifier.MyService;


@Named
@ViewScoped
public class DeviceController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int number;
	
	private User user;
	
	private Device device;
	
	@Inject
	Logger logger;

	
	@Inject
	@MyService
	private UserService userService;
	
	@Inject
	@MyService
	private DeviceService deviceService;


	
	@PostConstruct
	public void init(){
		logger.info("### init profilcontroller");
		user = new User();
		device = new Device();
		user = userService.getLoginUser();
	
	}
	
	
	public String addDevice(){
//		device = new Device();
		return Pages.INDEX;
	}
	
	public List<Device> listDevices(){
		return deviceService.getAllDevicess();
	}
	
	
	public void deleteDevice(Device device){
		deviceService.deleteDevice(device);
	}
	
	public String doSave(){
		logger.info("### device");
			logger.info("+# "+device.getName());
			
			
				
			logger.info("### Current User"+user.getFirstName());

			deviceService.addDeviceWithUser(device, user);
		
		return Pages.INDEX;
	}


	public Device getDevice() {
		return device;
	}


	public void setDevice(Device device) {
		this.device = device;
	}
	
    public void increment() {
        number++;
    }
    
    public int getNumbe(){
    	return number;
    }
    
    public Device updateSensor(){
    List<Device> list =	deviceService.getAllDevicess();
    	
    	device = list.get(0);
    	return device;
    	
    }
    
    public String updateSensor(Device dev){
    	logger.info(" #### ID "+dev.getId());
    	Device newdevice = deviceService.getUpdateDevice(dev);
    	if(newdevice.isSensor()){
    		return "geschlossen";
    	}
    	else{
    		return "offen";
    	}
    	
    }
	
    public String updateTemp(Device dev){
    	logger.info(" #### ID "+dev.getId());
    	Device newdevice = deviceService.getUpdateDevice(dev);
    	return newdevice.getTemperatur();
    	
    }
    
    public String updateHum(Device dev){
    	logger.info(" #### ID "+dev.getId());
    	Device newdevice = deviceService.getUpdateDevice(dev);
    	return newdevice.getHumidity();
    	
    }
	
	

}
