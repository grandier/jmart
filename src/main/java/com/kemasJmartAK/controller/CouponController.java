package com.kemasJmartAK.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.kemasJmartAK.*;
import com.kemasJmartAK.dbjson.*;

/**
 * Coupon controller is used to handle request about Coupon class
 * @author Kemas Rafly Omar Thoriq
 */
@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon> {

	@JsonAutowired(value = Coupon.class, filepath = "Coupon.json")
	public static JsonTable<Coupon> couponTable;

	@Override
	public JsonTable<Coupon> getJsonTable() {
		return couponTable;
	}

	/**
	 * 
	 * @param id mencari id 
	 * @return apakah datanya sudah dipakai atau tidak
	 */
	@GetMapping("/{id}/isUsed")
	boolean isUsed(@RequestParam int id) {
		for (Coupon data : couponTable) {
			if (data.id == id) {
				return data.isUsed();
			}
		}
		return false;
	}

	/**
	 * 
	 * @param id mencari id
	 * @param price menerima harga daripada barangnya
	 * @param discount menerima diskon dari barangnya
	 * @return apakan kuponnya bisa digunakan
	 */
	@GetMapping("/{id}/canApply")
	boolean canApply(@RequestParam int id, @RequestParam double price, @RequestParam double discount) {
		for (Coupon data : couponTable) {
			if (data.id == id) {
				return data.canApply(price, discount);
			}
		}
		return false;
	}

	/**
	 * 
	 * @param page untuk menunjukan ada berapa banyak halaman
	 * @param pageSize untuk menunjukan berapa banyak 1 list dalam 1 halaman
	 * @return paginate
	 */
	@GetMapping("/getAvailable")
	@ResponseBody
	List<Coupon> getAvailable(@RequestParam int page, @RequestParam int pageSize) {
		return Algorithm.paginate(couponTable, page, pageSize, pred -> !pred.isUsed());
	}

	/**
	 * untuk mengecek coupon berdasarkan id
	 */
	@GetMapping("/{id}")
	public Coupon getById(int id) {
		return getJsonTable().get(id);
	}

	/**
	 * untuk paginate
	 */
	@Override
	@GetMapping("/page")
	public List<Coupon> getPage(int page, int pageSize) {
		return getJsonTable().subList(page, pageSize);
	}
}
