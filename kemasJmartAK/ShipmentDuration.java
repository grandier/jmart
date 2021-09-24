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
            bit = bit | s.bit;
        }//0000 1100 = 0000 1100 | 0001 0000 
    }

    public boolean isDuration(ShipmentDuration reference) {
        if(reference == null) {
            return true;
        }
        else {
            return (bit & reference.bit) != 0;
        }// 0000 0110 & 0000 0100 = 0000 0100 (4)   0 != 0 = false,  4 != 0 (true). 
    }
}
