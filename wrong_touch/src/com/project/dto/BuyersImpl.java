package com.project.dto;

public class BuyersImpl implements Buyers {

	private String name;
	private String email;
	private String password;
	private String phne;
	public BuyersImpl(String name, String email, String password, String phne) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phne = phne;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhne() {
		return phne;
	}
	public void setPhne(String phne) {
		this.phne = phne;
	}

	
	
}
