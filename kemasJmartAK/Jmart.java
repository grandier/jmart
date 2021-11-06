package kemasJmartAK;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

/**
 * Write a description of class Jmart here.
 *
 * @author Kemas Rafly Omar Thoriq
 * 
 */
public class Jmart
{
    public static void main(String[] args) {
    	String filepath = "C:\\Users\\rafly\\Documents\\PTN Stuff\\UI Kuliah\\Semester 3\\Pemrograman Berorientasi Objek - 02\\Praktikum\\Modul 6\\randomProductList.json";
        try {
            List<Product> list = read(filepath);
            List<Product> filtered = filterByPrice(list, 0.0, 20000.0);
            filtered.forEach(product -> System.out.println(product.price));
        } 
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public static List<Product> filterByCategory (List<Product>list, ProductCategory category){
        List<Product> tempHasil = new ArrayList<Product>();

        for(Product temp : list){
            if(temp.category == category){
                tempHasil.add(temp);
            }
        }
        return tempHasil;
    }
    
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
    
    public static List<Product> read(String filepath) throws FileNotFoundException {
        Gson gson = new Gson();
        
        Type userListType = new TypeToken<ArrayList<Product>>() {
        }.getType();
        
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        
        List<Product> returnList = gson.fromJson(br, userListType);
        
        return returnList;
    }
}
