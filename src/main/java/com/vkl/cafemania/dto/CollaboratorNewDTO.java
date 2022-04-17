package com.vkl.cafemania.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.vkl.cafemania.services.validation.CollaboratorInsert;

@CollaboratorInsert
public class CollaboratorNewDTO implements Serializable{


	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "field cannot be empty")
	@Length(min = 4, max = 120, message = "precise field have to be betwem 5 to 100")
	private String name;
	@NotEmpty(message = "field cannot be empty")
	@Email(message = "Invalid email")
	private String email;
	@NotEmpty(message = "field cannot be empty")
	@CPF
	private String cpf;
	@NotEmpty(message = "field cannot be empty")
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
