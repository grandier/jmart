package kemasJmartAK;

import java.util.Date;
import java.util.ArrayList;

/**
 * Abstract class Invoice - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Invoice extends Recognizable implements FileParser
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
    public Status status;
    public ArrayList<Record> history;

    protected Invoice(int id, int buyerId, int productId) {
        super(id);
        this.buyerId = buyerId;
        this.productId = productId;
        date = new Date();
        this.rating = Rating.NONE;
        this.status = Status.WAITING_CONFIRMATION;
    }

    @Override
    public boolean read(String content) {
        return false;
    }

    public abstract double getTotalPay();

    public class Record {
        public Status status;
        public Date date;
        public String message;
    }
    
}
