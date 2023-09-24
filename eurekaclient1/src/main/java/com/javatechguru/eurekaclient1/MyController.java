package com.javatechguru.eurekaclient1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/")
	String getStr() {
		return "from client1 ";
	}

	@GetMapping("/fromSchool")
	ResponseEntity<String> all() {
		//ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/", String.class);
		ResponseEntity<String> response = restTemplate.getForEntity("http://school", String.class);
		return response;
	}

}
