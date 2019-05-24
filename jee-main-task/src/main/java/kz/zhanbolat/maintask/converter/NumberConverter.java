package kz.zhanbolat.maintask.converter;

import java.math.BigDecimal;

import kz.zhanbolat.maintask.valid.NumberValidator;

public class NumberConverter {
	
	public static BigDecimal[] parseIntoNumbers(String numbers[]) {
		BigDecimal parsedNumbers[] = new BigDecimal[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			parsedNumbers[i] = parseIntoNumber(numbers[i]);
		}
		return parsedNumbers;
	}
	
	public static BigDecimal parseIntoNumber(String number) throws NumberFormatException {
		if (!NumberValidator.getInstance().isNumber(number)) {
			throw new NumberFormatException();
		}
		return new BigDecimal(number);
	}
	
}
