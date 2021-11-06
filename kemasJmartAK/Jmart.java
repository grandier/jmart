package kemasJmartAK;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;

/**
 * Write a description of class Jmart here.
 *
 * @author Kemas Rafly Omar Thoriq
 * 
 */
public class Jmart
{
    public static void main(String[] args) {
        System.out.println("account id: " + new Account(null, null, null, -1).id);
        System.out.println("account id: " + new Account(null, null, null, -1).id);
        System.out.println("account id: " + new Account(null, null, null, -1).id);
        
        System.out.println("payment id: " + new Payment(-1, -1, -1, null).id);
        System.out.println("payment id: " + new Payment(-1, -1, -1, null).id);
        System.out.println("payment id: " + new Payment(-1, -1, -1, null).id);
    }
    
    /*public static List<Product> filterByCategory(List<Product> list, ProductCategory category){
    	
    	List<Product> result = new ArrayList<Product>();
    	for (Product product : list) {
    		if (minPrice != 0.0 && product.price < minPrice) {
    			continue;
    		}
    		
    		if (maxPrice != 0.0 && product.price > maxPrice) {
    			continue;
    		}
    		result.add(product);
    	}
    	return result;
    }*/
    
    public static List<Product> filterByPrice(List<Product> list, double minPrice, double maxPrice)
    {
        List<Product> result = new ArrayList<Product>();
        for (Product product : list)
        {
            if (minPrice <= 0.0 && product.price < minPrice)
            {
                continue;
            }
            if (maxPrice <= 0.0 && product.price > maxPrice)
            {
                continue;
            }
            result.add(product);
        }
        return result;
    }
}
