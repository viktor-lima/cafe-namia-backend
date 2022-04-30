package com.vkl.cafemania.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vkl.cafemania.domain.Collaborator;
import com.vkl.cafemania.dto.CollaboratorNewDTO;
import com.vkl.cafemania.repositories.CollaboratorRepository;
import com.vkl.cafemania.resources.exceptions.FieldMessage;
import com.vkl.cafemania.services.validation.utils.BR;

public class CollaboratorInsertValidator implements ConstraintValidator<CollaboratorInsert, CollaboratorNewDTO> {
	
	@Autowired
	private CollaboratorRepository repo;
	
	
	@Override
	public void initialize(CollaboratorInsert ann) {
	}

	@Override
	public boolean isValid(CollaboratorNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		// include the tests here, inserting errors in the list
		
		if(!BR.isValidCPF(objDto.getCpf())) 
			list.add(new FieldMessage("cpf", "invalid CPF"));
		
		Collaborator aux = repo.findByCpf(objDto.getCpf());
		if(aux != null)
			list.add(new FieldMessage("cpf", "cpf already registered"));
		
		Collaborator collaborator = repo.findByEmail(objDto.getEmail());
		if (collaborator != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
