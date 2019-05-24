package kz.zhanbolat.maintask.action;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumbersAction {
	
	private static NumbersAction numberAction;
	
	private NumbersAction() { }
	
	public static NumbersAction getInstance() {
		if (numberAction == null) {
			numberAction = new NumbersAction();
		}
		return numberAction;
	}
	
	public BigDecimal calculateSum(BigDecimal numbers[]) {
		BigDecimal sum = BigDecimal.ZERO;
		for (BigDecimal number : numbers) {
			sum = sum.add(number);
		}
		return sum;
	}
	
	public BigDecimal calculateAvg(BigDecimal numbers[]) {
		BigDecimal avg = calculateSum(numbers);
		avg = avg.divide(BigDecimal.valueOf(numbers.length), 4, RoundingMode.HALF_UP);
		return avg;
	}
	
}
