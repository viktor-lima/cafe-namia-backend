package com.vkl.cafemania.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vkl.cafemania.domain.Collaborator;
import com.vkl.cafemania.domain.Item;
import com.vkl.cafemania.dto.ItemDTO;
import com.vkl.cafemania.dto.ItemNewDTO;
import com.vkl.cafemania.repositories.ItemRepository;
import com.vkl.cafemania.security.UserSS;
import com.vkl.cafemania.services.exceptions.AuthorizationException;
import com.vkl.cafemania.services.exceptions.DataIntegrityException;
import com.vkl.cafemania.services.exceptions.ObjectNotFoundException;

@Service
public class ItemService {

	@Autowired
	private ItemRepository repo;

	@Autowired
	private CollaboratorService collaboratorService;
	@Autowired
	private EmailService emailService;

	public Item find(Integer id) {
		Optional<Item> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Item.class.getName(), null));
	}

	public Item insert(Item obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Item update(Item obj) {
		Item newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Item newObj, Item obj) {
		newObj.setName(obj.getName());
		newObj.setDescription(obj.getDescription());
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Unable to delete a Collaborator that has items");
		}
	}

	public List<Item> findAll() {
		return repo.findAll();
	}

	public Page<Item> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		UserSS user = UserService.authenticated();
		if (user == null)
			throw new AuthorizationException("access denied");
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Collaborator collaborator = collaboratorService.find(user.getId());

		return repo.findByCollaborator(collaborator, pageRequest);
	}
	
	public Item fromDTO(ItemDTO objDto) {
		return new Item(objDto.getId(), objDto.getName(), objDto.getDescription(), null);
	}

	public Item fromDTO(ItemNewDTO objDto) {
		Collaborator collaborator = collaboratorService.find(objDto.getCollaborator_id());
		Item item = new Item(null, objDto.getName(), objDto.getDescription(), collaborator);
		emailService.sendOrderConfirmationEmail(item);
		return item;
	}

}
