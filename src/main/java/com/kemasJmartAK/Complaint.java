package com.kemasJmartAK;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.kemasJmartAK.dbjson.Serializable;

/**
 * Class to store complaint related information with message and date of complaint
 *
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 */
public class Complaint extends Serializable
{
    public Date date;
    public String desc;

    public Complaint(String desc) {
        this.desc = desc;
        date = new Date();
    }

    public String toString(){
    	SimpleDateFormat SDformat = new SimpleDateFormat("dd/MM/yyyy");
        String formatDate = SDformat.format(this.date);
        return "{date = " + formatDate + "desc = '" + this.desc + "'}";
    }
}
