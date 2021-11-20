package com.kemasJmartAK;

import com.kemasJmartAK.dbjson.Serializable;

/**
 * Write a description of class Product here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Product extends Serializable
{
	public int accountId;
	public double discount;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public ProductCategory category;
    public double price;
    public byte shipmentPlans;
    

    public Product(int accountId, String name, int weight, boolean conditionUsed,
    double price, double discount, ProductCategory category, byte shipmentPlans) {
        this.accountId = accountId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.shipmentPlans = shipmentPlans;
    }

    public String toString(){
    	return "name: " + (String)this.name + "\n" + "weight: " + (int)this.weight + "\n" 
    	+ "conditionUsed: " +  (boolean)this.conditionUsed + "Discount: " + (Double)this.discount 
    	+ "\n" + "Category: " + this.category + "\n" + "Price: " + (double)this.price + "\n" + "ShipmentPlans: " 
    	+ this.shipmentPlans;
    }
    
}
