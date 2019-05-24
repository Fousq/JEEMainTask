package kz.zhanbolat.maintask.reporter;

public class ErrorReporter {
	
	public static void report(String context) {
		System.err.println("Error: " + context);
	}
	
}
