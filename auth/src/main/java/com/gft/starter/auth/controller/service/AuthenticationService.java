package com.gft.starter.auth.controller.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gft.starter.core.model.Nutritionist;
import com.gft.starter.core.model.Person;
import com.gft.starter.core.model.PersonLogin;
import com.gft.starter.core.repository.NutritionistRepository;
import com.gft.starter.core.repository.PersonRepository;

@Service
public class AuthenticationService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private NutritionistRepository nutritionistRepository;
	
	public Optional<Nutritionist> cadastrarUsuario(Nutritionist nutritionist) {
		if (personRepository.findByLoginPerson(nutritionist.getLoginPerson()).isPresent()){
			System.out.println("Ta igual amigo");
			return Optional.empty();
		}
			
		nutritionist.setPasswordPerson(criptografarSenha(nutritionist.getPasswordPerson()));
		
		return Optional.of(nutritionistRepository.save(nutritionist));
	}
	
	public Optional<PersonLogin> autenticarUsuario(PersonLogin personLogin){

		Optional<Person> person = personRepository.findByLoginPerson(personLogin.getLoginPerson());
		
		if(person.isPresent()){
			if(compararSenhas(personLogin.getPasswordPerson(),person.get().getPasswordPerson())){

				PersonLogin AuthPersonLogin = new PersonLogin(person);
				return Optional.ofNullable(AuthPersonLogin);
			}
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais não coincidem", null);
		}
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário sem cadastro", null);
	}

	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaBanco);
	}
	
	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();		
		return encoder.encode(senha);
	}
}
