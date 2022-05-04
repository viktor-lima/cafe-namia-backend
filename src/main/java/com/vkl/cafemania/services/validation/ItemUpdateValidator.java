package com.vkl.cafemania.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.vkl.cafemania.domain.Item;
import com.vkl.cafemania.dto.ItemDTO;
import com.vkl.cafemania.dto.ItemNewDTO;
import com.vkl.cafemania.repositories.ItemRepository;
import com.vkl.cafemania.resources.exceptions.FieldMessage;

public class ItemUpdateValidator implements ConstraintValidator<ItemUpdate, ItemDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ItemRepository repo;
	
	
	@Override
	public void initialize(ItemUpdate ann) {
	}

	@Override
	public boolean isValid(ItemDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt( map.get("id"));
		
		
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
