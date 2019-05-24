package kz.zhanbolat.maintask.generator;

import kz.zhanbolat.maintask.action.FileAction;

public class DataGenerator {
	
	private StringBuilder data;
	
	public DataGenerator() {
		data = new StringBuilder();
	}
	
	public String generate(Number bound) {
		NumberGenerator numberGenerator = new NumberGenerator();
		String number = String.valueOf(numberGenerator.generate(bound.intValue()));
		while(number.length() + data.length() < FileAction.MAX_SIZE_OF_DATA) {
			data.append(number + "\n");
			number = String.valueOf(numberGenerator.generate(bound.intValue()));
		}
		return data.toString();
	}
	
}
