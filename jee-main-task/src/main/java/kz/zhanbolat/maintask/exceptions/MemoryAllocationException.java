package kz.zhanbolat.maintask.exceptions;

public class MemoryAllocationException extends MemoryException {
	
	public MemoryAllocationException() {
		super();
	}
	
	public MemoryAllocationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MemoryAllocationException(String message) {
		super(message);
	}
	
	public MemoryAllocationException(Throwable cause) {
		super(cause);
	}
	
}
