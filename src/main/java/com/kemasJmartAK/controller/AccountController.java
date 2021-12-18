package com.kemasJmartAK.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.*;

import com.kemasJmartAK.Account;
import com.kemasJmartAK.Store;
import com.kemasJmartAK.dbjson.*;

/**
 * The controller for any request with account class and implements BasicGetController.
 * this controller using rest controller to communicate
 *
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 */
@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
	public static final String REGEX_EMAIL = "^\\w+([\\.&`~-]?\\w+)*@\\w+([\\.-]?\\w+)+$";
	public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d][^-\\s]{7,}$";
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
	@JsonAutowired(value = Account.class, filepath = "Account.json")
	public static JsonTable<Account> accountTable;

	/**
	 * 
	 * @param email yang dimasukan pada register
	 * @param password yang dibuatkan juga dari user
	 * @return akan mengembalikan data jika seluruh email dan password benar
	 */
	@PostMapping("/login")
	Account login(@RequestParam String email, @RequestParam String password) {
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
		for (Account data : accountTable) {

			if (data.email.equals(email) && data.password.equals(password)) {
				return data;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param name dari pada user yang akan register
	 * @param email dari pada user yang akan register
	 * @param password dari pada user yang akan register
	 * @return akan mereturn akun yang baru saja di registerkan
	 */
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

	/**
	 * 
	 * @param id untuk mengecek id akun apa yang lagi ter-logged in
	 * @param name daripada toko yang akan dibuatkan
	 * @param address daripada toko yang akan dibuatkan
	 * @param phoneNumber daripada toko yang akan dibuatkan
	 * @return akan mereturn store yang baru saja di registerkan berdasarkan akun yang lagi login
	 */
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

	/**
	 * 
	 * @param id daripada akun yang ingin topup
	 * @param balance daripada akun yang ingin topup
	 * @return mengembalikan boolean dengan kondisi berhasil atau tidak topup-nya
	 */
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

	/**
	 * untuk mengecek akun berdasarkan id
	 */
	@Override
	@GetMapping("/{id}")
	public Account getById(@PathVariable int id) {
		return BasicGetController.super.getById(id);
	}

	/**
	 * untuk melihat accountable
	 */
	@Override
	public JsonTable getJsonTable() {
		return accountTable;
	}

	/**
	 * untuk paginate
	 */
	@Override
	public List getPage(int page, int pageSize) {
		return BasicGetController.super.getPage(page, pageSize);
	}
}