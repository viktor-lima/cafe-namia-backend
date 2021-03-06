package com.vkl.cafemania.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vkl.cafemania.services.validation.ItemUpdate;

@Entity
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true)
	private String name;
	private String description;


	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "collaborator_id")
	private Collaborator collaborator;

	public Item() {
		// TODO Auto-generated constructor stub
	}

	public Item(Integer id, String name, String description, Collaborator collaborator) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.collaborator = collaborator;
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


	public Collaborator getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(Collaborator collaborator) {
		this.collaborator = collaborator;
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
		Item other = (Item) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("name: ");
		builder.append(getCollaborator().getName());
		builder.append(", item: ");
		builder.append(getName());
		builder.append(", description: ");
		builder.append(getDescription());
		builder.append("\n");
		return builder.toString();
	}

	
	
	

}
