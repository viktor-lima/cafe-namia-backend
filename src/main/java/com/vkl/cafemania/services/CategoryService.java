package com.vkl.cafemania.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vkl.cafemania.domain.Category;
import com.vkl.cafemania.repositories.CategoryRepository;

@Service
public class CategoryService{

	@Autowired
	private CategoryRepository repo;
	
	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElse(null);
	}

	public Category insert(Category obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	
	
}
