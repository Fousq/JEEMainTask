package kz.zhanbolat.maintask.exceptions;

public abstract class MemoryException extends Exception {
	
	public MemoryException() {
		super();
	}
	
	public MemoryException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MemoryException(String message) {
		super(message);
	}
	
	public MemoryException(Throwable cause) {
		super(cause);
	}
	
}
