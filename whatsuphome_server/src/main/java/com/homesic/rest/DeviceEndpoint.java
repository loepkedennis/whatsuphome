package com.homesic.rest;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.homesic.rest.model.ValueModel;
import com.homesic.service.interfaces.DeviceService;
import com.homesic.utils.TestQualifier.MyService;

@Path("/device")
@Stateless
public class DeviceEndpoint {

	@Inject
	Logger logger;
	
	@Inject
	@MyService
	DeviceService deviceService;

	
	@POST
	@Path("temp/{value}")
    @Produces(MediaType.APPLICATION_JSON)
	public void putDeviceTemp(ValueModel model, @PathParam("value") String value){
		logger.info("### RESTFUL Geräte_ID: "+value+" Temperatur: "+model.getTemperature()+" Hum: "+model.getHumidity()+" Sensor: "+model.getSensor());;
		boolean deviceFound = deviceService.findDevice(value);
		logger.info("### Device: "+ deviceFound);
		
		if(deviceFound){
			deviceService.updateData(value, model.getTemperature(), model.getHumidity(), model.getSensor());
		}
		
	}
	

	@GET
	@Path("test")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBoolean(){
		logger.info("### TEST");
		return "Test Ok";
	}

}
