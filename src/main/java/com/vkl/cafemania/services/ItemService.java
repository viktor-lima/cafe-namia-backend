package com.vkl.cafemania.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vkl.cafemania.domain.Item;
import com.vkl.cafemania.repositories.ItemRepository;
import com.vkl.cafemania.services.exceptions.DataIntegrityException;

@Service
public class ItemService{

	@Autowired
	private ItemRepository repo;
	
	public Item find(Integer id) {
		Optional<Item> obj = repo.findById(id);
		return obj.orElse(null);
	}

	public Item update(Item obj) {
		Item newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Item newObj, Item obj) {
		newObj.setName(obj.getName());
		newObj.setDescription(obj.getDescription());
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Unable to delete a Collaborator that has items");
		}
	}

	public List<Item> findAll() {
		return repo.findAll();
	}
	
	public Page<Item> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

}
