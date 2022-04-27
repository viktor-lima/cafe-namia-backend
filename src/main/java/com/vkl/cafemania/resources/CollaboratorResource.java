package com.vkl.cafemania.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vkl.cafemania.domain.Collaborator;
import com.vkl.cafemania.dto.CollaboratorDTO;
import com.vkl.cafemania.dto.CollaboratorNewDTO;
import com.vkl.cafemania.services.CollaboratorService;

@RestController
@RequestMapping(value = "/collaborators")
public class CollaboratorResource {
	
	@Autowired
	private CollaboratorService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Collaborator> find(@PathVariable Integer id) {
		Collaborator obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ResponseEntity<CollaboratorDTO> find(@RequestParam(value = "value") String email) {
		Collaborator obj = service.findByEmail(email);
		return ResponseEntity.ok().body(new CollaboratorDTO(obj));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CollaboratorNewDTO objDto){
		Collaborator obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update (@Valid @RequestBody CollaboratorDTO objDto, @PathVariable Integer id){
		Collaborator obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Collaborator>> findAll() {
		List<Collaborator> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	public ResponseEntity<Page<CollaboratorDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy
			) {
		Page<Collaborator> list = service.findPage(page,linesPerPage,direction,orderBy);
		Page<CollaboratorDTO> objDto = list.map(obj -> new CollaboratorDTO(obj)); 
		return ResponseEntity.ok().body(objDto);
	}
}
