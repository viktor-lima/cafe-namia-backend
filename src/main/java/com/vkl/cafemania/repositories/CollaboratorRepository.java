package com.vkl.cafemania.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vkl.cafemania.domain.Category;
import com.vkl.cafemania.domain.Collaborator;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Integer>{

	
}
