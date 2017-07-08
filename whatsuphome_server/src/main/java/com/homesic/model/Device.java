package com.homesic.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@NamedQueries({
	@NamedQuery(name = Device.findAll, query = "SELECT c FROM Device c"),
	@NamedQuery(name = Device.findDeviceId, query = "SELECT c FROM Device c WHERE c.token =:token")
	
})


@Entity
@Table(name="DEVICE")
public class Device {
	
	public static final String findAll = "Devices.findAll";
	public static final String findDeviceId = "Devices.findDeviceId";
	
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long id;
	
	private String name;
	
	private String token;
	
	private String temperatur;
	
	private String humidity;
	
	private boolean sensor;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name = "USER_DEVICE",
			joinColumns = { @JoinColumn(name = "DEVICE_CAT", referencedColumnName = "id") },
			inverseJoinColumns = { @JoinColumn(name = "USER_CAT", referencedColumnName = "id")})
	private List<User> user = new ArrayList<User>();
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getTemperatur() {
		return temperatur;
	}

	public void setTemperatur(String temperatur) {
		this.temperatur = temperatur;
	}


	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public boolean isSensor() {
		return sensor;
	}

	public void setSensor(boolean sensor) {
		this.sensor = sensor;
	}


	

}
