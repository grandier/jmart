package kemasJmartAK;


/**
 * Write a description of class Account here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Account extends Recognizable implements FileParser
{
    public String name;
    public String email;
    public String password;

    public Account (int id, String name, String email, String password){
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean read(String content) {
        return false;
    }

    public String toString(){
        return "name: " + (String)this.name + "\n" + "email: " + (String)this.email + "\n" + "password: " + (String)this.password;
    }
}
