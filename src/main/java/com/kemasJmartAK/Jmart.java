package com.kemasJmartAK;

/*import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;*/

import com.kemasJmartAK.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

/**
 * Main class that contains all class wants to run.
 *
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 */
public class Jmart {
	public static void main(String[] args) {
		JsonDBEngine.Run(Jmart.class);
		SpringApplication.run(Jmart.class, args);
		Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
	}

	/*
	public static List<Product> filterByCategory(List<Product> list, ProductCategory category) {
		return Algorithm.<Product>collect(list, prod -> prod.category == category);
	}

	public static List<Product> filterByPrice(List<Product> list, double minPrice, double maxPrice) {
		if (minPrice <= 0) {
			return Algorithm.<Product>collect(list, prod -> prod.price <= maxPrice);
		} else if (maxPrice <= 0) {
			return Algorithm.<Product>collect(list, prod -> prod.price >= minPrice);
		}
		return Algorithm.<Product>collect(list, prod -> prod.price <= maxPrice && prod.price >= minPrice);
	}

	public static List<Product> read(String filepath) throws FileNotFoundException {
		Gson gson = new Gson();

		Type userListType = new TypeToken<ArrayList<Product>>() {
		}.getType();

		BufferedReader br = new BufferedReader(new FileReader(filepath));

		List<Product> returnList = gson.fromJson(br, userListType);

		return returnList;
	}

	public static List<Product> filterByAccountId(List<Product> list, int accountId, int page, int pageSize) {
		return paginate(list, page, pageSize, product -> product.accountId == accountId);
	}

	public static List<Product> filterByName(List<Product> list, String search, int page, int pageSize) {
		return paginate(list, page, pageSize, product -> product.name.toLowerCase().contains(search.toLowerCase()));
	}

	private static List<Product> paginate(List<Product> list, int page, int pageSize, Predicate<Product> pred) {
		int iteration = 0;
		int occurences = 0;
		int startingIdx = page * pageSize;
		List<Product> pageList = new ArrayList<>(pageSize);

		for (; iteration < list.size() && occurences < startingIdx; ++iteration) {
			if (pred.predicate(list.get(iteration))) {
				++occurences;
			}
		}
		for (int i = iteration; i < list.size() && pageList.size() < pageSize; ++i) {
			if (pred.predicate(list.get(i))) {
				pageList.add(list.get(i));
			}
		}
		return pageList;
	}
	*/
}
