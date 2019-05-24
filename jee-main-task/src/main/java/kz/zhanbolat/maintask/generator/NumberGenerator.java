package kz.zhanbolat.maintask.generator;

import java.util.Random;

public class NumberGenerator {

	private Random random;
	
	public NumberGenerator() {
		random = new Random();
	}

	public Number generate(Number bound) {
		return random.nextBoolean() ? random.nextInt(bound.intValue())
				: random.nextDouble();
	}
	
}
