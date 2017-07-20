package org.jaxrs.api;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="user")
public class User implements Serializable {

	/**
	 * Serial 1L
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	
	
	private String name;
	
	
	private String profession;

	public User(int id, String name, String profession) {
		super();
		this.id = id;
		this.name = name;
		this.profession = profession;
	}

	public User() {
		super();		
	}

	public int getId() {
		return id;
	}
	
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getProfession() {
		return profession;
	}

	@XmlElement
	public void setProfession(String profession) {
		this.profession = profession;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj == null) {
			return false;
		} else if(!(obj instanceof User)) {
			return false;
		}
		User user = (User) obj;
		if(id==user.getId() && name.equals(user.getName()) && profession.equals(user.getProfession())) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
	

}