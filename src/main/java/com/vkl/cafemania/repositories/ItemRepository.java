package com.vkl.cafemania.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.cafemania.domain.Item;

@Repository
public interface ItemRepository  extends JpaRepository<Item, Integer>{

	@Transactional(readOnly = true)
	Item findByName(String name);
}
