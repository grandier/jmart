package com.kemasJmartAK;

/**
 * phoneTopUp is used to fill up the balance and what to pay
 * @author Kemas Rafly Omar Thoriq
 *
 */
public class PhoneTopUp extends Invoice{
	public String phoneNumber;
	public Status status;
	
	public PhoneTopUp(int buyerId, int productId, String phoneNumber) {
		super(buyerId, productId);
		this.phoneNumber = phoneNumber;
	}

	@Override
	public double getTotalPay(Product product) {
		return product.price * product.discount;
	}
}
