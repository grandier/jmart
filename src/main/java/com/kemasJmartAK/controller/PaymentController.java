package com.kemasJmartAK.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.kemasJmartAK.*;
import com.kemasJmartAK.ObjectPoolThread;
import com.kemasJmartAK.Payment;
import com.kemasJmartAK.dbjson.*;

/**
 * Payment controller is used to handle request Payment class
 * @author Kemas Rafly Omar Thoriq
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
	public static final long DELIVERED_LIMIT_MS = 100;
	public static final long ON_DELIVERY_LIMIT_MS = 100;
	public static final long ON_PROGRESS_LIMIT_MS = 100;
	public static final long WAITING_CONF_LIMIT_MS = 100;
	@JsonAutowired(value = Payment.class, filepath = "Payment.json")
	public static JsonTable<Payment> paymentTable;
	ObjectPoolThread<Payment> poolThread;

	/**
	 * untuk melihat paymenttable
	 */
	@Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

	/**
	 * untuk mengecek payment berdasarkan id
	 */
    @Override
    public Payment getById(int id) {
        return BasicGetController.super.getById(id);
    }

    /**
	 * untuk paginate
	 */
    @Override
    public List<Payment> getPage(int page, int pageSize) {
        return BasicGetController.super.getPage(page, pageSize);
    }

    /**
     * 
     * @param id yang sedang dicari
     * @return apakah payment diaccept atau tidak
     */
    @PostMapping("/{id}/accept")
    public boolean accept
            (
                    @PathVariable Integer id
            )
    {
        for(Payment each : paymentTable){
            if(each.id == id){
                if(each.history.get(each.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION){
                    each.history.add(new Payment.Record(Invoice.Status.ON_PROGRESS, null));
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 
     * @param id yang sedang dicari
     * @return apakah payment dicancel atau tidak
     */
    @PostMapping("/{id}/cancel")
    public boolean cancel(
           @PathVariable int id
        )
    {
        for (Payment iterate : paymentTable) {
            if (iterate.id == id) {
                if (iterate.history.get(iterate.history.size() - 1).status == Invoice.Status.WAITING_CONFIRMATION) {
                    iterate.history.add(new Payment.Record(Invoice.Status.CANCELLED, null));
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 
     * @param buyerId akun yang ingin membeli
     * @param productId product yang ingin dibeli
     * @param productCount banyak product yang ingin dibeli
     * @param shipmentAddress shipment address daripada akun yang membeli
     * @param shipmentPlan shipment plan daripada product itu sendiri
     * @return
     */
    @PostMapping("/create")
    public Payment create
            (
                    @RequestParam int buyerId,
                    @RequestParam int productId,
                    @RequestParam int productCount,
                    @RequestParam String shipmentAddress,
                    @RequestParam byte shipmentPlan
            )
    {
        Product product1 = Algorithm.<Product>find(ProductController.productTable,e -> e.id == productId);
        Account account1 = Algorithm.<Account>find(AccountController.accountTable,e -> e.id == buyerId);
        if(product1 != null && account1 != null ){
            Payment payment = new Payment(buyerId,productId,productCount,new Shipment(shipmentAddress,0,shipmentPlan,null));
            if(payment.getTotalPay(product1) > account1.balance){
                return null;
            }else {
                account1.balance -= payment.getTotalPay(product1);
                payment.history.add(new Payment.Record(Invoice.Status.WAITING_CONFIRMATION," "));
                paymentTable.add(payment);
                return payment;
            }
        }else {
            return null;
        }
    }

    /**
     * 
     * @param id daripada payment yang sedang berlangsung
     * @param receipt mengembali receipt apabila seluruh payment sudah selesai
     * @return
     */
    @PostMapping("/{id}/submit")
    public boolean submit
            (
                    @PathVariable int id,
                    @RequestParam String receipt
            )
    {
        for(Payment each : paymentTable){
            if(each.id == id){
                if(each.history.get(each.history.size()-1).status == Invoice.Status.ON_PROGRESS){
                    if(!receipt.isBlank()){
                        each.shipment.receipt = receipt;
                        each.history.add(new Payment.Record(Invoice.Status.ON_DELIVERY, null));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean timekeeper(Payment payment) {
        Payment.Record record = payment.history.get(payment.history.size() - 1);
        long elapsed = System.currentTimeMillis() - record.date.getTime();
        if (record.status.equals(Invoice.Status.WAITING_CONFIRMATION) && (elapsed > WAITING_CONF_LIMIT_MS)) {
            record.status = Invoice.Status.FAILED;
            return true;
        } else if (record.status.equals(Invoice.Status.ON_PROGRESS) && (elapsed > ON_PROGRESS_LIMIT_MS)) {
            record.status = Invoice.Status.FAILED;
            return true;
        } else if (record.status.equals(Invoice.Status.ON_DELIVERY) && (elapsed > ON_PROGRESS_LIMIT_MS)) {
            record.status = Invoice.Status.FINISHED;
            return true;
        } else if (record.status.equals(Invoice.Status.FINISHED) && (elapsed > DELIVERED_LIMIT_MS)) {
            record.status = Invoice.Status.FINISHED;
            return true;
        } else {
            return false;
        }
    }
	
}
