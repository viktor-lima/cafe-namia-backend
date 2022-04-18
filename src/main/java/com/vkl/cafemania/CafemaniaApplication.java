package com.vkl.cafemania;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vkl.cafemania.domain.Category;
import com.vkl.cafemania.domain.Collaborator;
import com.vkl.cafemania.domain.Item;
import com.vkl.cafemania.repositories.CategoryRepository;
import com.vkl.cafemania.repositories.CollaboratorRepository;
import com.vkl.cafemania.repositories.ItemRepository;

@SpringBootApplication
public class CafemaniaApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private CollaboratorRepository collaboratorRepository;

	public static void main(String[] args) {
		SpringApplication.run(CafemaniaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category category1 = new Category(null, "Salgados");
		Category category2 = new Category(null, "Doces");
		Category category3 = new Category(null, "Bebidas");
		Category category4 = new Category(null, "Frutas");
		Category category5 = new Category(null, "Organicos");
		
		Collaborator collaborator1 = new Collaborator(null, "viktor", "viktor@gmail.com", "12475144475");
		Collaborator collaborator2 = new Collaborator(null, "tata", "tata@gmail.com", "12475194499");
		
		Item item1 = new Item(null, "coxinha", "cosinha de frango fit", category1, collaborator1);
		Item item2 = new Item(null, "Pão de queijo", "", category1, collaborator1);
		Item item3 = new Item(null, "Bolo", "Bolo de morango", category2,collaborator2);
		Item item4 = new Item(null, "suco graviola", "sem açucar", category3,collaborator2);
		
		category1.getItems().addAll(Arrays.asList(item1,item2));
		category2.getItems().addAll(Arrays.asList(item3));
		category3.getItems().addAll(Arrays.asList(item4));
		
		

		categoryRepository.saveAll(Arrays.asList(category1,category2, category3,category4,category5));
		
		collaborator1.getItems().addAll(Arrays.asList(item1, item2));
		collaborator1.getPhones().addAll(Arrays.asList("965268651"));
		
		
		collaborator2.getItems().addAll(Arrays.asList(item3, item4));
		
		collaboratorRepository.saveAll(Arrays.asList(collaborator1,collaborator2));
		itemRepository.saveAll(Arrays.asList(item1, item2,item3,item4));
	}
	
	

}
