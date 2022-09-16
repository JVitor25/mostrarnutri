package com.gft.starter.user.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.starter.core.model.Diet;
import com.gft.starter.core.model.Group;
import com.gft.starter.core.model.User;
import com.gft.starter.core.repository.DietRepository;
import com.gft.starter.core.repository.GroupRepository;
import com.gft.starter.core.repository.UserRepository;

@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DietRepository dietRepository;

	@Autowired
	private GroupRepository groupRepository ;
	
	@GetMapping("/mydiet/a")
	public ResponseEntity<List<User>> teste(){
		return ResponseEntity.ok(userRepository.findAll());
	}

	@GetMapping("/mydiet/{uuidUser}")
	public ResponseEntity<Diet> getDiet(@PathVariable UUID uuidUser){
		System.out.println(uuidUser);
		Optional<User> user = userRepository.findById(uuidUser);
		Optional<Group> group = groupRepository.findById(user.get().getGroup().getUuidGroup());
		Optional<Diet> diet = dietRepository.findById(group.get().getDiet().getUuidDiet());
		UUID uuidDiet = diet.get().getUuidDiet();
		return dietRepository.findById(uuidDiet).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@GetMapping("/mypayments/{uuidUser}")
	public ResponseEntity<User> getById(@PathVariable UUID uuidUser){
		Optional<User> user = userRepository.findById(uuidUser);
		return userRepository.findById(uuidUser).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());	
	}
}
