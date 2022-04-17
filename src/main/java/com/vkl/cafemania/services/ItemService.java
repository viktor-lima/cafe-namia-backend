package com.vkl.cafemania.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vkl.cafemania.domain.Category;
import com.vkl.cafemania.domain.Item;
import com.vkl.cafemania.repositories.ItemRepository;

@Service
public class ItemService{

	@Autowired
	private ItemRepository repo;
	
	public Item find(Integer id) {
		Optional<Item> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
}
