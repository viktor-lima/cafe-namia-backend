package com.vkl.cafemania.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vkl.cafemania.domain.Collaborator;
import com.vkl.cafemania.repositories.CollaboratorRepository;
import com.vkl.cafemania.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private CollaboratorRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Collaborator collaborator = repo.findByEmail(email);
		if(collaborator == null)
			throw new UsernameNotFoundException(email);
		return new UserSS(collaborator.getId(), collaborator.getEmail(), collaborator.getPassword(), collaborator.getProfiles());
	}

}
