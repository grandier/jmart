package com.kemasJmartAK.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kemasJmartAK.Account;
import com.kemasJmartAK.ObjectPoolThread;
import com.kemasJmartAK.Payment;
import com.kemasJmartAK.dbjson.JsonAutowired;
import com.kemasJmartAK.dbjson.JsonTable;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Account>
{
    public static final long DELIVERED_LIMIT_MS = 1;
    public static final long ON_DELIVERY_LIMIT_MS = 1;
    public static final long ON_PROGRESS_LIMIT_MS = 1;
    public static final long WAITING_CONF_LIMIT_MS = 1;

    @JsonAutowired(filepath = "/json/payment.json", value = Payment.class)
    public static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread; // = new ObjectPoolThread<Payment>("Thread-PP", PaymentController::timekeeper);

    @PostMapping("/{id}/accept")
    boolean accept(int id){
        return false;
    }
    @PostMapping("/{id}/cancel")
    boolean cancel(int id){
        return false;
    }
    @PostMapping("/create")
    Payment create(int buyerId, int productId, int productCount, String shipmentAddress){
        return null;
    }
    @PostMapping("/getJsonTable")
    public JsonTable<Account> getJsonTable(){
        return null;
    }
    @PostMapping("/{id}/submit")
    boolean submit(int id, String receipt){
        return false;
    }



}
