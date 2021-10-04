package kemasJmartAK;

import java.util.regex.Matcher;
import java.util.regex.Pattern; 

/**
 * Write a description of class Store here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Store extends Recognizable implements FileParser
{
    public static final String REGEX_PHONE = "^(\\d{9,12})$";
    public static final String REGEX_NAME = "^(?=^[A-Z])(?![A-Z a-z]{20,})((?=[A-Z a-z]{4,}).)((?!\\s{2}).)*$";
    public String name;
    public String address;
    public String phoneNumber;

    public Store(int accountId, String name, String address, String phoneNumber) {
        super(accountId);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Store(Account account, String name, String address, String phoneNumber) {
        super(account.id);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public boolean read(String content) {
        return false;
    }

    public String toString(){
        return "name: " + (String)this.name + "\n" + "address: " + (String)this.address + "\n" + "phoneNumber: " + (String)this.phoneNumber;
    }

    public boolean validate(){
        Pattern formatNum = Pattern.compile(REGEX_PHONE);
        Matcher cekPhone = formatNum.matcher(phoneNumber);
        boolean matchPhone = cekPhone.find();
        
        Pattern formatName = Pattern.compile(REGEX_NAME);
        Matcher cekName = formatName.matcher(name);
        boolean matchName = cekName.find();

        if(matchName == true && matchPhone == true) {
            return true;
        }
        else {
            return false;
        }
    }
}
