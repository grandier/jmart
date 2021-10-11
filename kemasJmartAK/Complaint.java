package kemasJmartAK;

import java.text.SimpleDateFormat;
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
        this.desc = desc;
        date = new Date();
    }

    @Override
    public boolean read(String content) {
        return false;
    }

    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(this.date);
        String complaint = "Complaint{date=" + date + ", desc='" + this.desc + "'}";
        return complaint ;
    }
}
