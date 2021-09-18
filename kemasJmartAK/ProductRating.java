package kemasJmartAK;


/**
 * Write a description of class ProductRating here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ProductRating
{
    private long total;
    private long count;

    public ProductRating() {
        this.total = 0;
        this.count = 0;
    }

    public void insert(int rating) {
        this.total += rating;
        this.count++;
    }

    public double getAverage() {
        if (this.count == 0){
            System.out.println("Tidak bisa dibagi dengan angka 0!");
            return 0;
        }
        else {
            return (double) this.total / this.count;
        }
    }

    public long getCount() {
        return this.count;
    }

    public long getTotal() {
        return this.total;
    }
}
