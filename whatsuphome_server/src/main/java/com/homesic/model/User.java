package com.homesic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;



@NamedQueries({
	@NamedQuery(name = User.findByEmail, query = "SELECT o FROM User o WHERE o.email =:email"),
	
})
@Entity
@Table(name="USER")
public class User implements Serializable {
	
	public static final String findByEmail = "User.findByEmail";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id;
	
	@Column(name="FIRSTNAME")
	private String firstName;
	
	@Column(name="LASTNAME")
	private String lastName;
	
	
	@Column(name="EMAIL", unique=true)
	private String email;
	
	@Transient
	private String reemail;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Transient
	private String repassword;
	
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="USER_ROLES", joinColumns={@JoinColumn(name="USER_ID")}, inverseJoinColumns={@JoinColumn(name="ROLES_ID")})
	private Collection<Role> roles = new LinkedList<Role>();

	
	@ManyToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Device> devices = new ArrayList<Device>();
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getReemail() {
		return reemail;
	}


	public void setReemail(String reemail) {
		this.reemail = reemail;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRepassword() {
		return repassword;
	}


	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}


	public Collection<Role> getRoles() {
		return roles;
	}


	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}


	public List<Device> getDevices() {
		return devices;
	}


	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	
	

	
	
	
	
}
