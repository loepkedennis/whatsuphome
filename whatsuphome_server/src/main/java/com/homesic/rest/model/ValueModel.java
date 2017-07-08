package com.homesic.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ValueModel {
	
	String temperature;
	String humidity;
	String sensor;
	
	public ValueModel(){
		
	}

	public ValueModel(String tempreature, String humidity, String sensor ){
		this.temperature = tempreature;
		this.humidity = humidity;
		this.sensor = sensor;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getSensor() {
		return sensor;
	}

	public void setSensor(String sensor) {
		this.sensor = sensor;
	}
	
	
	
	
	

}