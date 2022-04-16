package com.vkl.cafemania.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vkl.cafemania.domain.Category;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Category> find() {
		Category category = new Category(1, "Doces");
		Category category2 = new Category(1, "Salgados");
		
		List<Category> listCat = new ArrayList<>();
		listCat.add(category);
		listCat.add(category2);
		
		return listCat;
		
		
	}
	
}
