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
        int newPrice;
        if (discountPercentage > 100.0f){
            return 0;
        }

        else {
            newPrice = price - (int) ((float) price * (discountPercentage / 100));
            return newPrice;
        }
    }

    static int getOriginalPrice(int getDiscountedPrice, float discountPercentage) {
        int oldPrice;
        float salePricePercentage;

        if (getDiscountedPrice <= 0 ){
            return 0;
        }
        
        salePricePercentage = 1 - (discountPercentage / 100);
        oldPrice = (int) (getDiscountedPrice / salePricePercentage);
        return oldPrice;
    }

    static float getCommissionMultiplier() {
        float commissionMultiplier = (float) 0.05;
        return commissionMultiplier;
    }

    static int getAdjustedPrice(int price) {
        int adjustedPrice = 0;
        double doublePrice = price;

        adjustedPrice = price + (int)(doublePrice * getCommissionMultiplier());
        return adjustedPrice;
    }

    static int getAdminFee(int price) {
        int adminFee = 0;
        double doublePrice = price;

        adminFee = (int)(doublePrice * getCommissionMultiplier());
        return adminFee;
    }
}
