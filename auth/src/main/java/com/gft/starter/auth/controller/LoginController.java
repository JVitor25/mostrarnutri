package com.gft.starter.auth.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.starter.auth.controller.service.AuthenticationService;
import com.gft.starter.auth.utility.JwtUtility;
import com.gft.starter.core.model.JwtToken;
import com.gft.starter.core.model.Nutritionist;
import com.gft.starter.core.model.PersonLogin;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	private JwtUtility jwtUtility;
	
	@Autowired
	private AuthenticationService  authenticationService;

	@PostMapping("/login")
	public JwtToken authenticate(@RequestBody PersonLogin personLogin) throws Exception {
		
		Optional<PersonLogin> authPersonLogin = authenticationService.autenticarUsuario(personLogin);
		System.out.println(authPersonLogin.get().getUuidPerson());
		
		final String token = jwtUtility.generateToken(authPersonLogin);
		
		return new JwtToken("Bearer "+token);
		 
	}
	
	@PostMapping("/register/nutritionist")
	public ResponseEntity<Nutritionist> post (@Valid @RequestBody Nutritionist nutritionist){
		//Person oPerson = (oNutritionist);
		//oNutritionist.setPasswordPerson(personService.registerPerson(oPerson).get().getPasswordPerson());
		return authenticationService.cadastrarUsuario(nutritionist)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

}