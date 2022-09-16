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

import com.gft.starter.core.model.Pay;
import com.gft.starter.core.repository.BookkeeperRepository;
import com.gft.starter.core.repository.PayRepository;
import com.gft.starter.core.repository.UserRepository;

@RestController
@RequestMapping("/bookkeeper/pay")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PayController {

	@Autowired
	PayRepository payRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BookkeeperRepository bookkeeperRepository;

	@GetMapping("/all")
	public ResponseEntity<List<Pay>> getAll() {
		return ResponseEntity.ok(payRepository.findAll());
	}

	@GetMapping("/find/{uuidPay}")
	public ResponseEntity<Pay> getById(@PathVariable UUID uuidPay) {
		return payRepository.findById(uuidPay).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/register")
	public ResponseEntity<Pay> post(@Valid @RequestBody Pay pay) {
		if ((userRepository.existsById(pay.getUser().getUuidPerson()))
				&& (bookkeeperRepository.existsById(pay.getBookkeeper().getUuidPerson()))) {
			return ResponseEntity.status(HttpStatus.CREATED).body(payRepository.save(pay));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	//quando for dar baixa como pago, utilizar o atualizar para mandar a
	//data de pagamento do valor
	@PutMapping("/update")
	public ResponseEntity<Pay> put(@Valid @RequestBody Pay pay) {
		if (payRepository.existsById(pay.getUuidPay())) {
			if ((userRepository.existsById(pay.getUser().getUuidPerson()))
					&& (bookkeeperRepository.existsById(pay.getBookkeeper().getUuidPerson()))) {
				return ResponseEntity.status(HttpStatus.OK).body(payRepository.save(pay));
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/delete/{uuidPay}")
	public void delete(@PathVariable UUID uuid) {
		payRepository.deleteById(uuid);
	}
}
