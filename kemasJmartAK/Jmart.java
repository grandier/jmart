package kemasJmartAK;

/*import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;*/

import com.google.gson.Gson;
/*import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;*/

/**
 * Write a description of class Jmart here.
 *
 * @author Kemas Rafly Omar Thoriq
 * 
 */
public class Jmart 
{
	public static long DELIVERED_LIMIT_MS = 5;
	public static long ON_DELIVERY_LIMIT_MS = 5;
	public static long ON_PROGRESS_LIMIT_MS = 5;
	public static long WAITING_CONF_LIMIT_MS = 5;
	
	public static void main(String[] args) {
		try {
			JsonTable<Payment> table = new JsonTable<>(Payment.class, "C:\\Users\\rafly\\Documents\\PTN Stuff\\UI Kuliah\\Semester 3\\Pemrograman Berorientasi Objek - 02\\Praktikum\\Modul 7\\randomPaymentList.json");
			ObjectPoolThread<Payment> paymentPool = new ObjectPoolThread<Payment>("Thread-PP", Jmart::paymentTimekeeper);
			paymentPool.start();
			table.forEach(payment -> paymentPool.add(payment));
			while (paymentPool.size() != 0);
			System.out.println("Thread exited successfully");
			Gson gson = new Gson();
			table.forEach(payment -> {
				String history = gson.toJson(payment.history);
				System.out.println(history);
			});
        }

        catch (Throwable t)
        {
            t.printStackTrace();
        }
	}
	
	public static boolean paymentTimekeeper(Payment payment) {
		long startTime = System.currentTimeMillis();
        if(System.currentTimeMillis() - startTime > WAITING_CONF_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FAILED, "FAILED"));
        }
        else if(System.currentTimeMillis() - startTime > ON_PROGRESS_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FAILED, "FAILED"));
        }
        else if(System.currentTimeMillis() - startTime > ON_DELIVERY_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.ON_DELIVERY, "ON_DELIVERY"));
        }
        else if(System.currentTimeMillis() - startTime > DELIVERED_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "DELIVERED"));
            return true;
        }
        return false;
	}
	
	
	/*public static List<Product> filterByCategory(List<Product> list, ProductCategory category) {
		return Algorithm.<Product>collect(list, prod -> prod.category == category);
	}
	
	public static List<Product> filterByPrice(List<Product> list, double minPrice, double maxPrice) {
		if (minPrice <= 0) {
			return Algorithm.<Product>collect(list, prod -> prod.price <= maxPrice);
		}
		else if (maxPrice <= 0) {
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
	}*/
}
