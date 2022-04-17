package com.vkl.cafemania.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.vkl.cafemania.domain.Collaborator;

public class CollaboratorDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	@NotEmpty(message = "field cannot be empty")
	@Length(min = 4, max = 120, message = "precise field have to be betwem 5 to 100")
	private String name;
	@NotEmpty(message = "field cannot be empty")
	@Email(message = "Invalid email")
	private String email;
	
	public CollaboratorDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public CollaboratorDTO(Collaborator obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
