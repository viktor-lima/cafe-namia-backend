package com.vkl.cafemania.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vkl.cafemania.domain.Category;
import com.vkl.cafemania.domain.Item;
import com.vkl.cafemania.services.ItemService;

@RestController
@RequestMapping(value = "/items")
public class ItemResource {
	
	@Autowired
	private ItemService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Item> find(@PathVariable Integer id) {
		
		Item obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
}
