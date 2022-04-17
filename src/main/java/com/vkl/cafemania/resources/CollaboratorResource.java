package com.vkl.cafemania.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vkl.cafemania.domain.Category;
import com.vkl.cafemania.domain.Collaborator;
import com.vkl.cafemania.services.CategoryService;
import com.vkl.cafemania.services.CollaboratorService;

@RestController
@RequestMapping(value = "/contributors")
public class CollaboratorResource {
	
	@Autowired
	private CollaboratorService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Collaborator> find(@PathVariable Integer id) {
		
		Collaborator obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
}
