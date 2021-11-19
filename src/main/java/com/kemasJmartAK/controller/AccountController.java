package com.kemasJmartAK.controller;

import com.kemasJmartAK.Account;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")

public class AccountController {
	@GetMapping
	String index() { return "account page"; }
	
	@PostMapping("/register")
	Account register
	(
		@RequestParam String name,
		@RequestParam String email,
		@RequestParam String password
	)
	{
		return new Account(name, email, password, 0);
	}
	
	@GetMapping("/{id}")
	String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}
