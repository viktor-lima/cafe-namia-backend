package com.vkl.cafemania.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vkl.cafemania.domain.Item;
import com.vkl.cafemania.dto.ItemDTO;
import com.vkl.cafemania.dto.ItemNewDTO;
import com.vkl.cafemania.repositories.ItemRepository;
import com.vkl.cafemania.resources.exceptions.FieldMessage;

public class ItemInsertValidator implements ConstraintValidator<ItemInsert, ItemDTO> {
	
	@Autowired
	private ItemRepository repo;
	
	
	@Override
	public void initialize(ItemInsert ann) {
	}

	@Override
	public boolean isValid(ItemDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		// include the tests here, inserting errors in the list
		
		Item aux = repo.findByName(objDto.getName());
		if(aux != null)
			list.add(new FieldMessage("name", "name already registered"));
		

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
