package com.kemasJmartAK.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.kemasJmartAK.*;
import com.kemasJmartAK.dbjson.*;

@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon> {

	@JsonAutowired(value = Coupon.class, filepath = "Coupon.json")
	public static JsonTable<Coupon> couponTable;

	@Override
	public JsonTable<Coupon> getJsonTable() {
		return couponTable;
	}

	@GetMapping("/{id}/isUsed")
	boolean isUsed(@RequestParam int id) {
		for (Coupon data : couponTable) {
			if (data.id == id) {
				return data.isUsed();
			}
		}
		return false;
	}

	@GetMapping("/{id}/canApply")
	boolean canApply(@RequestParam int id, @RequestParam double price, @RequestParam double discount) {
		for (Coupon data : couponTable) {
			if (data.id == id) {
				return data.canApply(price, discount);
			}
		}
		return false;
	}

	@GetMapping("/getAvailable")
	@ResponseBody
	List<Coupon> getAvailable(@RequestParam int page, @RequestParam int pageSize) {
		return Algorithm.paginate(couponTable, page, pageSize, pred -> !pred.isUsed());
	}
	
	@GetMapping("/{id}")
    public Coupon getById(int id) {
        return getJsonTable().get(id);
    }

    @GetMapping("/page")
    public List<Coupon> getPage(int page, int pageSize) {
        return getJsonTable().subList(page, pageSize);
    }
}
