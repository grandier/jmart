package com.kemasJmartAK.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kemasJmartAK.Account;
import com.kemasJmartAK.Store;
import com.kemasJmartAK.dbjson.JsonAutowired;
import com.kemasJmartAK.dbjson.JsonTable;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    public static final String REGEX_EMAIL = "^\\w+([.&`~-]?\\w+)*@\\w+([.-]?\\w+)+$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d][^-\\s]{8,}$";
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
    @JsonAutowired(value = Account.class,filepath = "Account.json")
    public static JsonTable<Account> accountTable;

    @GetMapping
    String index() { return "account page"; }

    @Override
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    @PostMapping("/login")
    @ResponseBody Account login
            (
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        for (Account each : accountTable){
            if(each.email.equals(email) && each.password.equals(password)){
                return each;
            }
        }
        return null;
    }

    @PostMapping("/register")
    Account register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {

        boolean hasilEmail = REGEX_PATTERN_EMAIL.matcher(email).find();
        boolean hasilPassword = REGEX_PATTERN_PASSWORD.matcher(password).find();
        if(!name.isBlank() || hasilEmail || hasilPassword ||
                accountTable.stream().anyMatch(account -> account.email.equals(email))){
            Account account =  new Account(name, email, password, 0);
            accountTable.add(account);
            return account;
        }
        return null;
    }

    @PostMapping("/{id}/registerStore")
    Store registerStore
            (
                    @RequestParam String id,
                    @RequestParam String name,
                    @RequestParam String address,
                    @RequestParam String phoneNumber
            )
    {
        for(Account each : accountTable){
            if (each.store == null){
                each.store = new Store(phoneNumber, name,address, 0);
                return each.store;
            }
        }
        return null;
    }

    @PostMapping("/{id}/topUp")
    boolean topUp
            (
                    @RequestParam int id,
                    @RequestParam double balance
            )
    {
        for(Account each : accountTable){
            if(each.id == id){
                each.balance += balance;
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