package com.kemasJmartAK;


/**
 * Product rating is storing rate of a product
 *
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
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
        count++;
    }
    
    public double getAverage() {
        if (count <= 0){
            return 0.0;
        }
        else {
            return (double) total / count;
        }
    }

    public long getCount() {
        return count;
    }

    public long getTotal() {
        return total;
    }
}
