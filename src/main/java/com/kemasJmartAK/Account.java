package com.kemasJmartAK;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kemasJmartAK.dbjson.Serializable; 

/**
 * Class as an account that store every data related to account information
 *
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 */
public class Account extends Serializable
{
    public static final String REGEX_EMAIL = "^\\w+([\\.&`~-]?\\w+)*@\\w+([\\.-]?\\w+)+$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d][^-\\s]{8,}$";
    public String name;
    public String email;
    public String password;
    public double balance;
    public Store store;

    public Account (String name, String email, String password, double balance){
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public boolean validate(){
        Pattern formatEmail = Pattern.compile(REGEX_EMAIL);
        Matcher cekEmail = formatEmail.matcher(email);
        boolean matchEmailcekEmail = cekEmail.find();
        
        Pattern formatPassword = Pattern.compile(REGEX_PASSWORD);
        Matcher cekPassword = formatPassword.matcher(password);
        boolean matchPassword = cekPassword.find();

        if(matchPassword == true && matchEmailcekEmail == true) {
            return true;
        }
        else {
            return false;
        }
    }
}
