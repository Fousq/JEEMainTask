package kz.zhanbolat.maintask.valid;

public class NumberValidator {
	
	private static NumberValidator numberValidator;
	
	private NumberValidator() { }
	
	public static NumberValidator getInstance() {
		if (numberValidator == null) {
			numberValidator = new NumberValidator();
		}
		return numberValidator;
	}
	
	public boolean isNumber(String number) {
		for (char character : number.toCharArray()) {
			if ( !(character == '.' || Character.isDigit(character) 
					|| character == 'E' || character == '-' 
					|| character == '+') ) {
				return false;
			}
		}
		return true;
	}
	
}
