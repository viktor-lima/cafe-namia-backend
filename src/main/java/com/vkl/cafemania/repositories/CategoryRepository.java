package com.vkl.cafemania.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vkl.cafemania.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	
}
