package com.homesic.utils;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Caputre implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String captureWert;
	
	private String captureWertVergleich;
	
	@PostConstruct
	public void CaptureZuweisung(){
		int zufallszahl = (int)(Math.random() * 200) + 100;
		setCaptureWert(Integer.toString(zufallszahl));
	}

	public String getCaptureWert() {
		return captureWert;
	}

	public void setCaptureWert(String captureWert) {
		this.captureWert = captureWert;
	}

	public String getCaptureWertVergleich() {
		return captureWertVergleich;
	}

	public void setCaptureWertVergleich(String captureWertVergleich) {
		this.captureWertVergleich = captureWertVergleich;
	}
	
	public boolean vergleich(){
		if(getCaptureWert() == getCaptureWertVergleich()){
			return true;
		}
		else return false;
	}
	
	

}
