package kemasJmartAK;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

/**
 * Write a description of class Jmart here.
 *
 * @author Kemas Rafly Omar Thoriq
 * 
 */
public class Jmart {
	public static void main(String[] args) {
		try {
			List<Product> list = read(
					"C:\\Users\\rafly\\Documents\\PTN Stuff\\UI Kuliah\\Semester 3\\Pemrograman Berorientasi Objek - 02\\Praktikum\\Modul 6\\randomProductList.json");
			List<Product> filteredByName = filterByName(list, "gtx", 1, 5);
            filteredByName.forEach(product -> System.out.println(product.name));
            List<Product> filteredById = filterByAccountId(list, 1, 0, 5);
            filteredById.forEach(product -> System.out.println(product.name));

        }

        catch (Throwable t)
        {
            t.printStackTrace();
        }
	}

	public static List<Product> filterByCategory(List<Product> list, ProductCategory category) {
		List<Product> tempHasil = new ArrayList<Product>();

		for (Product temp : list) {
			if (temp.category == category) {
				tempHasil.add(temp);
			}
		}
		return tempHasil;
	}

	public static List<Product> filterByPrice(List<Product> list, double minPrice, double maxPrice) {
		List<Product> result = new ArrayList<Product>();
		for (Product product : list) {
			if (minPrice <= 0.0 && product.price < minPrice) {
				continue;
			}
			if (maxPrice <= 0.0 && product.price > maxPrice) {
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
}
