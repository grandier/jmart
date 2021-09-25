package kemasJmartAK;

/**
 * Write a description of class Jmart here.
 *
 * @author Kemas Rafly Omar Thoriq
 * 
 */
public class Jmart
{
    public static void main(String[] args) {

    }

    public static Product create(){
        return null;
    }

    public static Product createProduct() {
        return null;
    }

    public static Coupon createCoupon() {
        return new Coupon("Kupon Discount 50%", 1, Coupon.Type.DISCOUNT, 50, 10000);
    }

    public static ShipmentDuration createShipmentDuration() {
        return new ShipmentDuration(ShipmentDuration.INSTANT, ShipmentDuration.SAME_DAY);
    }
}
