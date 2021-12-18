package com.kemasJmartAK.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.kemasJmartAK.*;
import com.kemasJmartAK.dbjson.*;

/**
 * Product Controller is used to handle product class
 * @author Kemas Rafly Omar Thoriq
 */
@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product> {

	@JsonAutowired(value = Product.class, filepath = "Product.json")
	public static JsonTable<Product> productTable;

	/**
	 * untuk melihat producttable
	 */
	@Override
	public JsonTable<Product> getJsonTable() {
		// TODO Auto-generated method stub
		return productTable;
	}

	/**
	 * 
	 * @param id daripada store itu sendiri
	 * @param page pilihan halaman
	 * @param pageSize jumlah list dalam 1 halaman
	 * @return seluruh product yang dijual oleh toko
	 */
	@GetMapping("/{id}/store")
	List<Product> getProductByStore(@RequestParam int id, @RequestParam int page, @RequestParam int pageSize) {
		return Algorithm.paginate(productTable, page, pageSize, pred -> pred.accountId == id);
	}

	/**
	 * 
	 * @param accountId akun yang membuat
	 * @param name dari product yang akan dibuat
	 * @param weight dari productnya
	 * @param conditionUsed product baru atau tidak
	 * @param price dari productnya
	 * @param discount dari productnya jika ada
	 * @param category dari productnya yang berhubungan dengan {@link ProductCategory}
	 * @param shipmentPlans dari productnya yang berhubungan dengan {@link Shipment}
	 * @return product untuk dijualkan dan dimasukan ke list
	 */
	@PostMapping("/create")
	@ResponseBody
	Product create(
						@RequestParam int accountId, 
						@RequestParam String name, 
						@RequestParam int weight,
						@RequestParam boolean conditionUsed, 
						@RequestParam double price, 
						@RequestParam double discount,
						@RequestParam ProductCategory category, 
						@RequestParam byte shipmentPlans
			) 
	{
		for (Account each : AccountController.accountTable) {
			if (each.id == accountId && each.store != null) {
				Product product = new Product(accountId, name, weight, conditionUsed, price, discount, category,
						shipmentPlans);
				productTable.add(product);
				return product;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param page jumlah halaman yang ada dari ingin melihat halaman apa
	 * @param pageSize jumlah list dalam satu halaman
	 * @param search nama dari productnya
	 * @param minPrice minimal harganya
	 * @param maxPrice harga maksnya
	 * @param conditionUsed kondisi dari productnya baru atau tidak
	 * @param category kategoru productnya 
	 * @return hasil daripada yang sudah difilter
	 */
	@GetMapping("/getFiltered")
    @ResponseBody
    List<Product> getProductByFilter
            (
                    @RequestParam int page,
                    @RequestParam int pageSize,
                    @RequestParam String search,
                    @RequestParam int minPrice,
                    @RequestParam int maxPrice,
                    @RequestParam boolean conditionUsed,
                    @RequestParam ProductCategory category
            )
    {
        List<Product> tempProduct = new ArrayList<Product>();
        for (Product each : productTable) {
            if (each.name.contains(search))
                if (minPrice <= each.price)
                    if (maxPrice >= each.price)
                        if (each.category == category)
                        	if(each.conditionUsed == conditionUsed)
                            tempProduct.add(each);
        }
        return Algorithm.paginate(tempProduct, page, pageSize,pred->pred.weight != 0);
    }
	
	/**
	 * untuk mengecek payment berdasarkan id
	 */
	@GetMapping("/{id}")
    public Product getById(int id) {
        return getJsonTable().get(id);
    }
	
	/*@GetMapping("/page")
    public List<Product> getPage(int page, int pageSize) {
        return getJsonTable().subList(page, pageSize);
    }*/
}
