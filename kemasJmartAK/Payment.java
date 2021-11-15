package kemasJmartAK;

import java.util.ArrayList;
import java.util.Date;

/**
 * Write a description of class Payment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Payment extends Invoice
{
	ArrayList<Record> history = new ArrayList<Record>();
    public int productCount;
    public Shipment shipment;

    public Payment(int buyerId, int productId, int productCount, Shipment shipment){
        super(buyerId, productId);
        this.productCount = productCount;
        this.productId = productId;
        this.shipment = shipment;
    }
    
    public double getTotalPay(Product product){
        return product.price * product.discount;
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
