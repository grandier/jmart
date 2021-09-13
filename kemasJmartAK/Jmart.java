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

    static int getPromo() {
        return 0;
    }

    static String getCustomer() {
        return "oop";
    }

    static float getDiscountPercentage(int before, int after) {
        float potongan = 0;
        if (before > after ) {
            potongan = before - after;
            potongan = potongan / 10;
        }
        if (before == after || before < after) {
            potongan = 0;
        }
        return potongan;
    }

    static int getDiscountedPrice(int price, float discountPercentage) {
        if (discountPercentage > 100.0f){
            return 0;
        }

        else {
            int newPrice = price - (int) ((float) price * (discountPercentage / 100.0f));
            return newPrice;
        }
    }

    static int getOriginalPrice(int getDiscountedPrice, float discountPercentage) {
        if (getDiscountedPrice <= 0 ){
            return 0;
        }
        
        float salePricePercentage = 1 - (discountPercentage / 100);
        int oldPrice = (int) (getDiscountedPrice / salePricePercentage);
        return oldPrice;
    }

    static float getCommissionMultiplier() {
        float commissionMultiplier = 0.05f;
        return commissionMultiplier;
    }

    static int getAdjustedPrice(int price) {
        double doublePrice = price;

        int adjustedPrice = price + (int)(doublePrice * getCommissionMultiplier());
        return adjustedPrice;
    }

    static int getAdminFee(int price) {

        int adminFee = (int) (price * getCommissionMultiplier());
        return adminFee;
    }
}
