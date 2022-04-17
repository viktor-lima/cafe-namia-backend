package com.vkl.cafemania.dto;

import java.io.Serializable;

public class CollaboratorNewDTO implements Serializable{


	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String cpf;
	
	private String phone1;
	private String phone2;
	
	public CollaboratorNewDTO() {
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	
	
	
	
	
}
