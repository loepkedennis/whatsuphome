//package com.homesic.data;
//
//import java.util.logging.Logger;
//
//import javax.enterprise.context.SessionScoped;
//import javax.enterprise.inject.Produces;
//import javax.faces.view.ViewScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import com.homesic.model.Device;
//import com.homesic.service.interfaces.DeviceService;
//import com.homesic.utils.TestQualifier.MyService;
//
//
//import java.io.Serializable;
//
//
//@Named
//@ViewScoped
//public class DeviceProducer implements Serializable {
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Inject
//	Logger logger;
//	
//	@Inject
//	@MyService
//	private DeviceService deviceService;
//	
//	private enum Mode{
//		EDIT, ADD;
//	}
//	
//	private Device device;
//	private Mode mode;
//	
//	@Produces
//	@Named
//	public Device getSelectedDevice(){
//		return device;
//	}
//	
//	@Produces
//	@Named
//	public void setSelectedDevice(Device device){
//		this.device = device;
//	}
//	
//	public boolean isAddMode(){
//		return mode == Mode.ADD;
//	}
//	
//	public void prepareAddDevice(){
//		
//		this.device = new Device();
//		
//		this.mode = Mode.ADD;	
//	}
//	
//	public void prepareEditDevice(Device device){
//		this.device = device;
//		
//		this.mode = Mode.EDIT;
//	}
//
//}
