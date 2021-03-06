package com.vkl.cafemania.services;

import org.springframework.mail.SimpleMailMessage;

import com.vkl.cafemania.domain.Collaborator;
import com.vkl.cafemania.domain.Item;

public interface EmailService {

	void sendOrderConfirmationEmail(Item obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Collaborator collaborator, String newPass);
}
