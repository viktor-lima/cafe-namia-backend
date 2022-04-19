package com.vkl.cafemania.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.vkl.cafemania.services.validation.ItemInsert;

@ItemInsert
public class ItemNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message = "field cannot be empty")
	@Length(min = 4, max = 100, message = "precise field have to be betwem 5 to 100")
	private String name;
	private String description;
	private Integer category_id;
	private Integer collaborator_id;
	
	public ItemNewDTO() {
		// TODO Auto-generated constructor stub
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public Integer getCollaborator_id() {
		return collaborator_id;
	}

	public void setCollaborator_id(Integer collaborator_id) {
		this.collaborator_id = collaborator_id;
	}
	
	

	
}
