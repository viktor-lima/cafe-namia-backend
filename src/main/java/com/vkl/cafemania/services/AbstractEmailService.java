package com.vkl.cafemania.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.vkl.cafemania.domain.Item;

public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(Item obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Item obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCollaborator().getEmail());
		sm.setFrom(sender);
		sm.setSubject("confirmed item! code: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

	
	
}
