package awt_1.util;

public class Util {
	public static double decrement(double value1, double value2) {
		if (value1 > 0) {
			value1 -= value2;
			if (value1 < 0) {
				return 0;
			} else {
				return value1;
			}
		} else {
			value1 += value2;
			if (value1 > 0) {
				return 0;
			} else {
				return value1;
			}
		}
	}
}
