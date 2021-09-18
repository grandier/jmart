package kemasJmartAK;

/**
 * Write a description of class PriceTag here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PriceTag
{
    public static double COMMISSION_MULTIPLIER = 0.05;
    public static double BOTTOM_PRICE = 20000.0;
    public static double BOTTOM_FEE = 1000.0;

    public double discount;
    public double price;

    public PriceTag(double price) {
        this.price = price;
        this.discount = 0.0;
    }

    public PriceTag(double price, double discount) {
        this.price = price;
        this.discount = discount;
    }


}
