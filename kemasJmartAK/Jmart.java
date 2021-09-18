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

    public static int getPromo() {
        return 0;
    }

    public static String getCustomer() {
        return "oop";
    }

    public static float getDiscountPercentage(int before, int after) {
        float potongan = 0;
        if (before > after ) {
            potongan = before - after;
            potongan = (potongan / before) * 100.0f;
        }
        if (before == after || before < after) {
            potongan = 0.0f;
        }
        return potongan;
    }

    public static int getDiscountedPrice(int price, float discountPercentage) {
        if (discountPercentage > 100.0f){
            return 0;
        }

        else {
            int newPrice = price - (int) ( price * (discountPercentage / 100.0f));
            return newPrice;
        }
    }

    public static int getOriginalPrice(int getDiscountedPrice, float discountPercentage) { 
        if (getDiscountedPrice <= 0 ){
            return 0;
        }
        
        float salePricePercentage = 1 - (discountPercentage / 100);
        int oldPrice = (int) (getDiscountedPrice / salePricePercentage);
        return oldPrice;
    }

    public static float getCommissionMultiplier() {
        float commissionMultiplier = 0.05f;
        return commissionMultiplier;
    }

    public static int getAdjustedPrice(int price) {
        double doublePrice = price;

        int adjustedPrice = price + (int)(doublePrice * getCommissionMultiplier());
        return adjustedPrice;
    }

    public static int getAdminFee(int price) {

        int adminFee = (int) (price * getCommissionMultiplier());
        return adminFee;
    }

    public static Product create(){
        return null;
    }
}
