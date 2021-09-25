package kemasJmartAK;


/**
 * Abstract class Transaction - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Transaction extends Recognizable
{
    public String time;
    public int buyerId;
    public int storeId;
    public Rating rating;
    
    public enum Rating{
        NONE, BAD, NEUTRAL, GOOD
    }
    
    protected Transaction(int id, int buyerId, int storeId) {
        super(id);
        this.buyerId = buyerId;
        this.storeId = storeId;
    }
    
    protected Transaction(int id, Account buyer, Store store) {
        super(id);
        this.buyerId = buyer.id;
        this.storeId = store.id;
    }
}
