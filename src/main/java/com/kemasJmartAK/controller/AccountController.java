package com.kemasJmartAK.controller;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.*;
import static org.apache.logging.log4j.util.Strings.isBlank;

import com.kemasJmartAK.Account;
import com.kemasJmartAK.Store;
import com.kemasJmartAK.dbjson.JsonAutowired;
import com.kemasJmartAK.dbjson.JsonTable;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController {
	public static final String REGEX_EMAIL = "^\\w+([\\.&`~-]?\\w+)*@\\w+([\\.-]?\\w+)+$";
	public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d][^-\\s]{8,}$";
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
	@JsonAutowired(value = Account.class, filepath = "akun.json")
	public static JsonTable<Account> accountTable;

	@PostMapping("/login")
	Account login(@RequestParam String email, @RequestParam String password) {
		for (Account data : accountTable) {
			if (data.email.equals(email) && data.password.equals(password)) {
				return data;
			}
		}
		return null;
	}

	@PostMapping("/register")
	Account register(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
		if (!isBlank(name) || !REGEX_PATTERN_EMAIL.matcher(email).find()
				|| !REGEX_PATTERN_PASSWORD.matcher(password).find()
				|| !accountTable.stream().anyMatch(account -> account.email.equals(email))) {
			return null;
		} else {
			return new Account(name, email, password, 0);
		}
	}

	@PostMapping("/{id}/registerStore")
	Store register(@RequestParam int id, @RequestParam String name, @RequestParam String address,
			@RequestParam String phoneNumber) {
		for (Account data : accountTable) {
			if (data.store == null && data.id == id) {
				data.store = new Store(name, address, phoneNumber, 0);
				return data.store;
			}
		}
		return null;
	}

	@PostMapping("/{id}/topUp")
	boolean topUp(@RequestParam int id, @RequestParam double balance) {
		for (Account data : accountTable) {
			if (data.id == id) {
				data.balance += balance;
				return true;
			}
		}
		return false;
	}

	@Override
	public Serializable getById(int id) {
		return BasicGetController.super.getById(id);
	}

	@Override
	public JsonTable getJsonTable() {
		return null;
	}

	@Override
	public List getPage(int page, int pageSize) {
		return BasicGetController.super.getPage(page, pageSize);
	}
}