package com.kemasJmartAK.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.*;

import com.kemasJmartAK.Account;
import com.kemasJmartAK.Store;
import com.kemasJmartAK.dbjson.*;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
	public static final String REGEX_EMAIL = "^\\w+([\\.&`~-]?\\w+)*@\\w+([\\.-]?\\w+)+$";
	public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d][^-\\s]{8,}$";
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
	@JsonAutowired(value = Account.class, filepath = "Account.json")
	public static JsonTable<Account> accountTable;

	@GetMapping
	String index() {
		return "account page";
	}

	@Override
	public JsonTable<Account> getJsonTable() {
		return accountTable;
	}

	@PostMapping("/login")
	Account login(@RequestParam String email, @RequestParam String password) {
		for (Account data : accountTable) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] bytes = md.digest();
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < bytes.length; i++) {
					sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
				}
				password = sb.toString();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			if (data.email.equals(email) && data.password.equals(password)) {
				return data;
			}
		}
		return null;
	}

	@PostMapping("/register")
	Account register(@RequestParam String name, @RequestParam String email, @RequestParam String password) {

		boolean hasilEmail = REGEX_PATTERN_EMAIL.matcher(email).find();
		boolean hasilPassword = REGEX_PATTERN_PASSWORD.matcher(password).find();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			password = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (!name.isBlank() && hasilEmail && hasilPassword
				&& !accountTable.stream().anyMatch(account -> account.email.equals(email))) {
			Account account = new Account(name, email, password, 0);
			accountTable.add(account);
			return account;
		}
		return null;
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
	
	@GetMapping("/{id}")
    public Account getById(int id) {
        return getJsonTable().get(id);
    }

    @GetMapping("/page")
    public List<Account> getPage(int page, int pageSize) {
        return getJsonTable().subList(page, pageSize);
    }
}