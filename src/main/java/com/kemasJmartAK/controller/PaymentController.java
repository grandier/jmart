package com.kemasJmartAK.controller;

import org.springframework.web.bind.annotation.*;

import com.kemasJmartAK.ObjectPoolThread;
import com.kemasJmartAK.Payment;
import com.kemasJmartAK.dbjson.JsonTable;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController {

	public static final long DELIVERED_LIMIT_MS = 0;
	public static final long ON_DELIVERIY_LIMIT_MS = 0;
	public static final long ON_PROGRESS_LIMIT_MS = 0;
	public static final long WAITING_CONF_LIMIT_MS = 0;
	public static JsonTable<Payment> paymentTable;
	public static ObjectPoolThread<Payment> poolThread;

	@Override
	public JsonTable<Payment> getJsonTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@PostMapping("/{id}/accept")
	public boolean accept(int id) {
		return false;
	}

	@PostMapping("/{id}/cancel")
	public boolean cancel(int id) {
		return false;
	}

	@PostMapping("/create")
	public Payment create(int buyerId, int productId, int productCount, String shipmentAddress, byte shipmentPlan) {
		return null;
	}

	@PostMapping("/submit")
	public boolean submit(int id, String receipt) {
		return false;
	}

	private static boolean timekeeper(Payment payment) {
		return false;
	}
}
