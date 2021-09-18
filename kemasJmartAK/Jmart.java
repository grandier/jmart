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
        return new Product ("Mobil", 100, false, new PriceTag(2000000.0), ProductCategory.AUTOMOTIVE);
    }
}
