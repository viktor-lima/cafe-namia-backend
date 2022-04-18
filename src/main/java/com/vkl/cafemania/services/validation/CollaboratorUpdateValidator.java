package com.vkl.cafemania.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.vkl.cafemania.domain.Collaborator;
import com.vkl.cafemania.dto.CollaboratorDTO;
import com.vkl.cafemania.repositories.CollaboratorRepository;
import com.vkl.cafemania.resources.exceptions.FieldMessage;
import com.vkl.cafemania.services.validation.utils.BR;

public class CollaboratorUpdateValidator implements ConstraintValidator<CollaboratorUpdate, CollaboratorDTO> {
	
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private CollaboratorRepository repo;
	
	
	@Override
	public void initialize(CollaboratorUpdate ann) {
	}

	@Override
	public boolean isValid(CollaboratorDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();

		// include the tests here, inserting errors in the list
		
		Collaborator aux = repo.findByEmail(objDto.getEmail());
		if(aux != null && !aux.getId().equals(uriId))
			list.add(new FieldMessage("email", "cpf already registered"));
		

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
