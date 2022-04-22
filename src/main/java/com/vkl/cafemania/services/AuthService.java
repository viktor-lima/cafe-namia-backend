package com.vkl.cafemania.services;

import java.util.Random;

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
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();

	public void sendNewPassword(String email) {
		Collaborator collaborator = collaboratorRepository.findByEmail(email);
		if(collaborator == null) {
			throw new ObjectNotFoundException("email not found");
		}
		
		String newPass = newPassword();
		collaborator.setPassword(coder.encode(newPass));
		
		collaboratorRepository.save(collaborator);
		//email 
		emailService.sendNewPasswordEmail(collaborator , newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if(opt == 0) { //generate a digit
			return (char)(rand.nextInt(10)+48);
		}
		else if(opt == 2) {//generate capital letter
			return (char)(rand.nextInt(26)+65);
		}
		else {
			return (char)(rand.nextInt(26)+97);
		}
	}
	
}
