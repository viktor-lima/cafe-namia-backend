package com.vkl.cafemania.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vkl.cafemania.domain.Collaborator;
import com.vkl.cafemania.domain.Item;
import com.vkl.cafemania.domain.enums.Profile;
import com.vkl.cafemania.repositories.CollaboratorRepository;
import com.vkl.cafemania.repositories.ItemRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder coder;

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private CollaboratorRepository collaboratorRepository;
	
	
	public void instantiateTestDataBase() {
	
		
		Collaborator collaborator1 = new Collaborator(null, "viktor", "alemaojk249@gmail.com", "12475144475", coder.encode("123"));
		Collaborator collaborator2 = new Collaborator(null, "tata", "tata@gmail.com", "12475194499", coder.encode("123"));
		collaborator2.addProfiles(Profile.ADMIN);
		
		Item item1 = new Item(null, "coxinha", "coxinha de frango fit", collaborator1);
		Item item2 = new Item(null, "Pão de queijo", "",  collaborator1);
		Item item3 = new Item(null, "Bolo", "Bolo de morango", collaborator2);
		Item item4 = new Item(null, "suco graviola", "sem açucar", collaborator2);
		
		
		collaborator1.getItems().addAll(Arrays.asList(item1, item2));
		collaborator1.getPhones().addAll(Arrays.asList("965268651"));
		
		
		collaborator2.getItems().addAll(Arrays.asList(item3, item4));
		
		collaboratorRepository.saveAll(Arrays.asList(collaborator1,collaborator2));
		itemRepository.saveAll(Arrays.asList(item1, item2,item3,item4));
		
	}
	
}
