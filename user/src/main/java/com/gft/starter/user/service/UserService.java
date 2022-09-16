package com.gft.starter.user.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.starter.core.model.Diet;
import com.gft.starter.core.model.Group;
import com.gft.starter.core.repository.DietRepository;
import com.gft.starter.core.repository.GroupRepository;

@Service
public class UserService {

	@Autowired
	private DietRepository dietRepository;

	@Autowired
	private GroupRepository groupRepository ;
	
	public Optional<Diet> getDiet(UUID uuid){
		Optional<Group> group = groupRepository.findById(uuid);
		Optional<Diet> diet = dietRepository.findById(group.get().getUuidGroup());
		return dietRepository.findById(diet.get().getUuidDiet());
	}
}
