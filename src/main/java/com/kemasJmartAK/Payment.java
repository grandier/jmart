package com.kemasJmartAK;

import java.util.ArrayList;
import java.util.Date;

/**
 * Payment extend invoice is a detail data of invoice that contains any data about payments
 *
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 */

public class Payment extends Invoice
{
    public int productCount;
    public Shipment shipment;
    public ArrayList<Record> history = new ArrayList<Record>();

    public Payment(int buyerId, int productId, int productCount, Shipment shipment){
        super(buyerId, productId);
        this.productCount = productCount;
        this.productId = productId;
        this.shipment = shipment;
    }
    
    @Override
    public double getTotalPay(Product product){
        return (product.price - (product.price * (product.discount/100))) * productCount;
    }

    public static class Record{
        public Status status;
        public Date date;
        public String message;

        public Record(Status status, String message){
            this.status = status;
            this.message = message;
            this.date = java.util.Calendar.getInstance().getTime();
        }
    }
}
