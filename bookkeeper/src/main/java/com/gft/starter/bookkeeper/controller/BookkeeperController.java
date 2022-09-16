package com.gft.starter.bookkeeper.controller;

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

import com.gft.starter.core.model.Bookkeeper;
import com.gft.starter.core.repository.BookkeeperRepository;


@RequestMapping("/bookkeeper")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BookkeeperController {

	@Autowired
	BookkeeperRepository bookkeeperRepository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Bookkeeper>> getAll(){
		return ResponseEntity.ok(bookkeeperRepository.findAll());
	}
	
	@GetMapping("/find/{uuidBookkeeper}")
	public ResponseEntity<Bookkeeper> getById(@PathVariable UUID uuidBookkeeper){
		return bookkeeperRepository.findById(uuidBookkeeper).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());	
	}
	
	@PostMapping("/register")
	public ResponseEntity<Bookkeeper> post (@Valid @RequestBody Bookkeeper oBookkeeper){
		//Person oPerson = (oBookkeeper);
		//oBookkeeper.setPasswordPerson(personService.registerPerson(oPerson).get().getPasswordPerson());
		return ResponseEntity.status(HttpStatus.CREATED).body(bookkeeperRepository.save(oBookkeeper));
	}
	
	@PutMapping("/update")
	public ResponseEntity<Bookkeeper> put(@Valid @RequestBody Bookkeeper bookkeeper){
		return ResponseEntity.ok(bookkeeperRepository.save(bookkeeper));
	}
	
	@DeleteMapping("/delete/{uuidPerson}")
	public void delete(@PathVariable UUID uuid) {
		bookkeeperRepository.deleteById(uuid);
	}
	
}
