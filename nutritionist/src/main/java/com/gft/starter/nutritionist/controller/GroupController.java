package com.gft.starter.nutritionist.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.starter.core.model.Group;
import com.gft.starter.core.repository.DietRepository;
import com.gft.starter.core.repository.GroupRepository;

@RestController // Definir que é uma controller
@RequestMapping("/nutritionist/group") // O caminho da url que acessará
@CrossOrigin(origins = "*", allowedHeaders = "*") // Permitir que venha de qualquer página a requisição
public class GroupController {

	@Autowired // injeção de dependência
	private GroupRepository groupRepository;

	@Autowired
	private DietRepository dietRepository;

	@GetMapping("/all")
	public ResponseEntity<List<Group>> getAll() {
		return ResponseEntity.ok(groupRepository.findAll());
	}

	@GetMapping("/find/{uuidGroup}")
	public ResponseEntity<Group> getById(@PathVariable UUID uuidGroup) {
		return groupRepository.findById(uuidGroup).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/register")
	public ResponseEntity<Group> post(@Valid @RequestBody Group group) {
		if (dietRepository.existsById(group.getDiet().getUuidDiet())) {
			return ResponseEntity.status(HttpStatus.CREATED).body(groupRepository.save(group));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PutMapping("/update")
	public ResponseEntity<Group> put(@Valid @RequestBody Group group) {
		if (groupRepository.existsById(group.getUuidGroup())) {
			if (dietRepository.existsById(group.getDiet().getUuidDiet())) {
				return ResponseEntity.status(HttpStatus.OK).body(groupRepository.save(group));
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/delete/{uuidGroup}")
	public void delete(@PathVariable UUID uuidGroup) {
		groupRepository.deleteById(uuidGroup);
	}
}
