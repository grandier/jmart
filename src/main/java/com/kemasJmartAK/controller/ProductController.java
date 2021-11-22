package com.kemasJmartAK.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.kemasJmartAK.*;
import com.kemasJmartAK.dbjson.*;

public class ProductController implements BasicGetController<Product> {

	@JsonAutowired(value = Product.class, filepath = "Product.json")
	public static JsonTable<Product> productTable;

	@Override
	public JsonTable<Product> getJsonTable() {
		// TODO Auto-generated method stub
		return productTable;
	}

	@GetMapping("/{id}/store")
	List<Product> getProductByStore(@RequestParam int id, @RequestParam int page, @RequestParam int pageSize) {
		return Algorithm.paginate(productTable, page, pageSize, pred -> pred.accountId == id);
	}

	@PostMapping("/create")
	@ResponseBody
	Product create(@RequestParam int accountId, @RequestParam String name, @RequestParam int weight,
			@RequestParam boolean conditionUsed, @RequestParam double price, @RequestParam double discount,
			@RequestParam ProductCategory category, @RequestParam byte shipmentPlans) {
		for (Product each : productTable) {
			if (each.accountId == accountId) {
				Product product = new Product(accountId, name, weight, conditionUsed, price, discount, category,
						shipmentPlans);
				productTable.add(product);
				return product;
			}
		}
		return null;
	}
	
	@GetMapping("/getFiltered")
    @ResponseBody
    List<Product> getProductByFilter
            (
                    @RequestParam int page,
                    @RequestParam int pageSize,
                    @RequestParam int accountId,
                    @RequestParam String search,
                    @RequestParam int minPrice,
                    @RequestParam int maxPrice,
                    @RequestParam ProductCategory category
            )
    {
        List<Product> tempProduct = new ArrayList<Product>();
        for (Product each : productTable) {
            if (each.accountId == accountId)
                if (each.name.contains(search))
                    if (minPrice <= each.price)
                        if (maxPrice >= each.price)
                            if (each.category == category)
                                tempProduct.add(each);
        }
        return tempProduct;
    }
	
	@GetMapping("/{id}")
    public Product getById(int id) {
        return getJsonTable().get(id);
    }
	
	@GetMapping("/page")
    public List<Product> getPage(int page, int pageSize) {
        return getJsonTable().subList(page, pageSize);
    }
}
