package com.vkl.cafemania.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.vkl.cafemania.domain.Item;
import com.vkl.cafemania.services.validation.ItemInsert;
import com.vkl.cafemania.services.validation.ItemUpdate;

@ItemUpdate
public class ItemDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message = "field cannot be empty")
	@Length(min = 4, max = 100, message = "precise field have to be betwem 5 to 100")
	private String name;
	private String description;
	
	public ItemDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ItemDTO(Item obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.description = obj.getDescription();
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

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemDTO other = (ItemDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	

}
