package com.kemasJmartAK;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.kemasJmartAK.dbjson.Serializable;

/**
 * Write a description of class Complaint here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
