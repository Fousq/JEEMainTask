package kz.zhanbolat.maintask.valid;

import kz.zhanbolat.maintask.action.FileAction;
import kz.zhanbolat.maintask.exceptions.MemoryAllocationException;

public class MemoryValidator {
	
	private static MemoryValidator memoryValidator;
	
	private MemoryValidator() { }
	
	public static MemoryValidator getInstance() {
		if (memoryValidator == null) {
			memoryValidator = new MemoryValidator();
		}
		return memoryValidator;
	}
	
	public boolean isMemorySizeValid(int size) 
			throws MemoryAllocationException {
		if (size > FileAction.MAX_SIZE_OF_DATA) {
			throw new 
				MemoryAllocationException("Not enough memory to store data");
		}
		return true;
	}
	
}
