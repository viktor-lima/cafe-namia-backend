package com.vkl.cafemania.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vkl.cafemania.domain.Collaborator;
import com.vkl.cafemania.repositories.CollaboratorRepository;
import com.vkl.cafemania.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private CollaboratorRepository collaboratorRepository;
	
	@Autowired
	private BCryptPasswordEncoder coder;

	public void sendNewPassword(String email) {
		Collaborator collaborator = collaboratorRepository.findByEmail(email);
		if(collaborator == null) {
			throw new ObjectNotFoundException("email not found");
		}
		
		String newPass = newPassword();
		collaborator.setPassword(coder.encode(newPass));
		
		collaboratorRepository.save(collaborator);
		//enviar email;
	}
	
}
