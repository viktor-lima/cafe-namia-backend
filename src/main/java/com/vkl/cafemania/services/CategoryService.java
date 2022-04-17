package com.vkl.cafemania.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.vkl.cafemania.domain.Category;
import com.vkl.cafemania.repositories.CategoryRepository;
import com.vkl.cafemania.services.exceptions.DataIntegrityException;
import com.vkl.cafemania.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService{

	@Autowired
	private CategoryRepository repo;
	
	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName(), null));
	}


	public Category insert(Category obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Category update(Category obj) {
		find(obj.getId());
		return repo.save(obj);
	}


	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Unable to delete a category that has items");
		}
	}


	public List<Category> findAll() {
		
		return repo.findAll();
	}
	
	
	
}
