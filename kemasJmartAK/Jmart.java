package kemasJmartAK;


/**
 * Write a description of class Jmart here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Jmart
{
    public static void main(String[] args) {
        getPromo();
        getCustomer();
        getDiscountPercentage(1000, 900);


        System.out.println(getPromo());
        System.out.println(getCustomer());
        System.out.println(getDiscountPercentage(1000, 900)+"f");
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
}
