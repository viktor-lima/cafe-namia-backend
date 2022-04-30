package com.vkl.cafemania.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.cafemania.domain.Collaborator;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Integer>{

	@Transactional(readOnly = true)
	Collaborator findByCpf(String cpf);
	@Transactional(readOnly = true)
	Collaborator findByEmail(String email);

}
