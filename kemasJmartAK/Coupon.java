package kemasJmartAK;

/**
 * Write a description of class Coupon here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Coupon extends Recognizable {
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

	public boolean canApply(Treasury treasury) {
		if (Treasury.getAdjustedPrice(this.minimum, this.cut) >= this.minimum && !this.used) {
            return true;
        } else {
        return false;
        }
    }

	public double Apply(Treasury treasury) {
		used = true;
		if (type == Type.DISCOUNT) {
			return (Treasury.getAdjustedPrice(this.minimum, this.cut) * ((100 - cut) / 100));
		} else {
			return (Treasury.getAdjustedPrice(this.minimum, this.cut) - cut);
		}
	}
}
