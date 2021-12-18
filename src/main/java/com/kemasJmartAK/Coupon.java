package com.kemasJmartAK;

import com.kemasJmartAK.dbjson.Serializable;

/**
 * Class to store coupon information and could be used when someone has a payment.
 * 
 * @author Kemas Rafly Omar Thoriq
 * @version 1.0
 */
public class Coupon extends Serializable {
	public final String name;
	public final int code;
	public final double cut;
	public final Type type;
	public final double minimum;
	private boolean used;

	public static enum Type {
		DISCOUNT, REBATE
	}

	public Coupon(String name, int code, Type type, double cut, double minimum) {
		this.name = name;
		this.code = code;
		this.cut = cut;
		this.type = type;
		this.minimum = minimum;
		used = false;
	}

	public boolean isUsed() {
		return used;
	}

	public boolean canApply(double price, double discount) {
		if (Treasury.getAdjustedPrice(price, discount) >= this.minimum && !this.used) {
            return true;
        } else {	
        return false;
        }
    }

	public double Apply(double price, double discount) {
		used = true;
		if (type == Type.DISCOUNT) {
			return (Treasury.getAdjustedPrice(price, discount) * ((100 - cut) / 100));
		} else {
			return (Treasury.getAdjustedPrice(price, discount) - cut);
		}
	}
}
