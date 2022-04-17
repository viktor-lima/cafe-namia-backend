package com.vkl.cafemania.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vkl.cafemania.domain.Category;
import com.vkl.cafemania.domain.Collaborator;
import com.vkl.cafemania.repositories.CategoryRepository;
import com.vkl.cafemania.repositories.CollaboratorRepository;

@Service
public class CollaboratorService{

	@Autowired
	private CollaboratorRepository repo;
	
	public Collaborator find(Integer id) {
		Optional<Collaborator> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
}
