package com.vkl.cafemania.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vkl.cafemania.domain.Category;
import com.vkl.cafemania.domain.Collaborator;
import com.vkl.cafemania.dto.CollaboratorDTO;
import com.vkl.cafemania.dto.CollaboratorNewDTO;
import com.vkl.cafemania.repositories.CollaboratorRepository;
import com.vkl.cafemania.services.exceptions.DataIntegrityException;
import com.vkl.cafemania.services.exceptions.ObjectNotFoundException;

@Service
public class CollaboratorService {

	@Autowired
	private CollaboratorRepository repo;

	public Collaborator find(Integer id) {
		Optional<Collaborator> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName(), null));
	}

	public Collaborator insert(Collaborator obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Collaborator update(Collaborator obj) {
		Collaborator newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Collaborator newObj, Collaborator obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Unable to delete a Collaborator that has items");
		}
	}

	public List<Collaborator> findAll() {
		return repo.findAll();
	}

	public Page<Collaborator> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Collaborator fromDTO(CollaboratorDTO objDto) {
		return new Collaborator(objDto.getId(), objDto.getName(), objDto.getEmail(), null);
	}

	public Collaborator fromDTO(CollaboratorNewDTO objDto) {
		Collaborator collaborator = new Collaborator(null, objDto.getName(), objDto.getEmail(), objDto.getCpf());
		collaborator.getPhones().add(objDto.getPhone1());

		if (objDto.getPhone2() != null)
			collaborator.getPhones().add(objDto.getPhone2());

		return collaborator;
	}
}
