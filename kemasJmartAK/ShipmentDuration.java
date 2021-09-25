package kemasJmartAK;

/**
 * Write a description of class ShipmentDuration here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ShipmentDuration
{                                                                                
    public static final ShipmentDuration INSTANT = new ShipmentDuration(1 << 0); 
    public static final ShipmentDuration SAME_DAY = new ShipmentDuration(1 << 1); 
    public static final ShipmentDuration NEXT_DAY = new ShipmentDuration(1 << 2);   
    public static final ShipmentDuration REGULER = new ShipmentDuration(1 << 3);
    public static final ShipmentDuration KARGO = new ShipmentDuration(1 << 4);  
    private int bit;

    private ShipmentDuration(int bit) {
        this.bit = bit; 
    }

    public ShipmentDuration(ShipmentDuration... args) {
        for(ShipmentDuration s : args) {
            this.bit = bit | s.bit;
        } 
    }

    public boolean isDuration(ShipmentDuration reference) {
        if((bit & reference.bit) != 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
