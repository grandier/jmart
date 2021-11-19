package com.kemasJmartAK;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Shipment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Shipment 
{
	public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("E MMMM dd yyyy");;
    public static final Plan INSTANT = new Plan((byte)(1 << 0));    //1
    public static final Plan SAME_DAY = new Plan((byte)(1 << 1));   //2
    public static final Plan NEXT_DAY = new Plan((byte)(1 << 2));   //4
    public static final Plan REGULER = new Plan((byte)(1 << 3));    //8
    public static final Plan KARGO = new Plan((byte)(1 << 4));      //16
    public int cost;
    public String address;
    public String receipt;
    private byte plan;

    public static class Plan{

        public final byte bit;

        private Plan(byte bit){
            this.bit = bit;
        }
    }

    public Shipment(String address, int cost, byte plan, String receipt){
        this.address = address;
        this.plan = plan;
        this.cost = cost;
        this.receipt = receipt;
    }
    public String getEstimatedArrival(Date reference){
        Calendar temp = Calendar.getInstance();
        if(this.plan == 1<<0|| this.plan == 1<<1){
            return ESTIMATION_FORMAT.format(reference.getTime());
        }else if(this.plan == 1<<2){
            temp.setTime(reference);
            temp.add(Calendar.DATE,1);
            return ESTIMATION_FORMAT.format(temp);
        }else if(this.plan == 1<<3){
            temp.setTime(reference);
            temp.add(Calendar.DATE,2);
            return ESTIMATION_FORMAT.format(temp);
        }else{
            temp.setTime(reference);
            temp.add(Calendar.DATE,5);
            return ESTIMATION_FORMAT.format(temp);
        }
    }

    public boolean isDuration(Plan reference){
        if((reference.bit & this.plan) != 0){
            return true;
        }
        return false;
    }

    public boolean isDuration(byte object,Plan reference){
        if((reference.bit & object) != 0){
            return true;
        }
        return false;
    }
}
