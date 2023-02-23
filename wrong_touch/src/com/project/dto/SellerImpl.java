package com.project.dto;

import java.util.Objects;

public class SellerImpl implements Seller {

	private String name;
	private String email;
	private String password;
	private String phne;
	public SellerImpl(String name, String email, String password, String phne) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phne = phne;
	}
	public SellerImpl() {
		// TODO Auto-generated constructor stub
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
	@Override
	public int hashCode() {
		return Objects.hash(email, name, password, phne);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SellerImpl other = (SellerImpl) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(phne, other.phne);
	}
	@Override
	public String toString() {
		return "SellerImpl [name=" + name + ", email=" + email + ", password=" + password + ", phne=" + phne + "]";
	}

	
}
