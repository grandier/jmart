package kemasJmartAK;

import java.util.Date;

/**
 * Write a description of class Complaint here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Complaint extends Recognizable implements FileParser
{
    public Date date;
    public String desc;

    public Complaint(int id, String desc) {
        super(id);
        this.desc = desc;
        date = new Date();
    }

    @Override
    public boolean read(String content) {
        return false;
    }
}
