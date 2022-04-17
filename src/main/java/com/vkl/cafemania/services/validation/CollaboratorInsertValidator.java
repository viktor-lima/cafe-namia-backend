package com.vkl.cafemania.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vkl.cafemania.dto.CollaboratorNewDTO;
import com.vkl.cafemania.resources.exceptions.FieldMessage;
import com.vkl.cafemania.services.validation.utils.BR;

public class CollaboratorInsertValidator implements ConstraintValidator<CollaboratorInsert, CollaboratorNewDTO> {
	@Override
	public void initialize(CollaboratorInsert ann) {
	}

	@Override
	public boolean isValid(CollaboratorNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		// include the tests here, inserting errors in the list
		
		if(!BR.isValidCPF(objDto.getCpf())) 
			list.add(new FieldMessage("cpf", "invalid CPF"));

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
