package kemasJmartAK;


/**
 * Write a description of class Product here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Product extends Recognizable implements FileParser
{
    public String name;
    public int weight;
    public boolean conditionUsed;
    public PriceTag priceTag;
    public ProductCategory category;
    public ProductRating rating;
    public int storeId;
    public Shipment.MultiDuration multiDuration;

    public Product(int id, int storeId, String name, int weight, boolean conditionUsed,
    PriceTag priceTag, ProductCategory category, Shipment.MultiDuration multiDuration) {
        super(id);
        this.storeId = storeId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;
        rating = new ProductRating();
        this.multiDuration = multiDuration;
    }

    @Override
    public boolean read(String content) {
        return false;
    }

    public String toString(){
        return "name: " + this.name + "\n" + "Weight: " + (int)this.weight + "\n" + "conditionUsed: " + (boolean)this.conditionUsed
        + "\n" + "priceTag: " + (double)this.priceTag.getAdjustedPrice() + "\n" + "Category: " + this.category + "\n" + "Category: " + this.category
        + "\n" + "rating: " + (int)this.rating.getAverage() + "\n" + "storeId: " + this.storeId;
    }
    
}
