package com.vkl.cafemania.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.vkl.cafemania.domain.Category;

public class CategoryDTO  implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message = "field cannot be empty")
	@Length(min = 5, max = 100, message = "precise field have to be betwem 5 to 100")
	private String name;
	
	public CategoryDTO() {
		// TODO Auto-generated constructor stub
	}

	public CategoryDTO(Category obj) {
		this.id = obj.getId();
		this.name = obj.getName();
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


	
}
