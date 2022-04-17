package com.vkl.cafemania;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vkl.cafemania.domain.Category;
import com.vkl.cafemania.domain.Item;
import com.vkl.cafemania.repositories.CategoryRepository;
import com.vkl.cafemania.repositories.ItemRepository;

@SpringBootApplication
public class CafemaniaApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ItemRepository itemRepository;

	public static void main(String[] args) {
		SpringApplication.run(CafemaniaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category category1 = new Category(null, "Salgados");
		Category category2 = new Category(null, "Doces");
		Category category3 = new Category(null, "Bebidas");
		
		
		Item item1 = new Item(null, "coxinha", "cosinha de frango fit", category1);
		Item item2 = new Item(null, "Pão de queijo", "", category1);
		Item item3 = new Item(null, "Bolo", "Bolo de morango", category2);
		Item item4 = new Item(null, "suco graviola", "sem açucar", category3);
		
		category1.getItems().addAll(Arrays.asList(item1,item2));
		category2.getItems().addAll(Arrays.asList(item3));
		category3.getItems().addAll(Arrays.asList(item4));
		
		item1.setCategory(category1);
		item2.setCategory(category1);
		item3.setCategory(category2);
		item4.setCategory(category3);
		

		categoryRepository.saveAll(Arrays.asList(category1,category2, category3));
		itemRepository.saveAll(Arrays.asList(item1, item2,item3,item4));
	}
	
	

}
