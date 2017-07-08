package com.homesic.service.interfaces;


import java.util.List;

import com.homesic.model.Device;
import com.homesic.model.User;



public interface DeviceService {
	
	void addDevice(Device device);
	
	void deleteDevice(Device device);
	
	void updateDevice(Device device);
	
	void addDeviceWithUser(Device device, User user);
	
	public List<Device> getAllDevicess();
	
	boolean findDevice(String deviceId);

	void updateData(String deviceId, String temperature, String humidity, String sensor);
	
	public Device getUpdateDevice(Device device);

}
