package com.kemasJmartAK;

import java.util.Date;

import com.kemasJmartAK.dbjson.Serializable;

/**
 * Invoice is storing data related to history and payment system.
 *
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 */
public abstract class Invoice extends Serializable
{
    public enum Status {
        WAITING_CONFIRMATION,
        CANCELLED,
        ON_PROGRESS,
        ON_DELIVERY,
        COMPLAINT,
        FINISHED,
        FAILED
    }

    public enum Rating {
        NONE, 
        BAD, 
        NEUTRAL, 
        GOOD
    }

    public Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;

    protected Invoice(int buyerId, int productId) {
        this.buyerId = buyerId;
        this.productId = productId;
        date = new Date();
        this.complaintId = -1;
        this.rating = Rating.NONE;
    }

    public abstract double getTotalPay(Product product);
    
}
