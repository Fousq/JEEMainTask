package kz.zhanbolat.maintask;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.junit.Test;

import kz.zhanbolat.maintask.action.FileAction;
import kz.zhanbolat.maintask.action.NumbersAction;
import kz.zhanbolat.maintask.converter.NumberConverter;
import kz.zhanbolat.maintask.valid.NumberValidator;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private static Logger logger = Logger.getLogger(AppTest.class);
    private File file;

	@Test
	public void testLoadData() {
		file = new File(getClass().getClassLoader()
										.getResource("LoadingTestFile.txt")
											.getFile());
		char data[] = FileAction.getInstance().loadData(file.getPath());
		String string = new String(data);
		logger.info(string);
		logger.info("String length: " + string.length());
		logger.info("Expected string length: "
							+ "HELLO, WORLD!".length());
		assertEquals("HELLO, WORLD!", string);
	}
	
	@Test
	public void testWriteData() {
		char data[] = new char[FileAction.MAX_SIZE_OF_DATA + 1];
		for (int i = 0; i < data.length; i++) {
			data[i] = 'a';
		}
		file = new File(getClass().getClassLoader()
										.getResource("WritingTestFile.txt")
											.getFile());
		FileAction.getInstance().writeData(file, String.copyValueOf(data));
	}
	
	@Test
	public void loadDataShouldThrowMemoryAllocationException() {
		file = new File(getClass().getClassLoader()
										.getResource("WritingTestFile.txt")
											.getFile());
		FileAction.getInstance().loadData(file.getPath());
	}
	
	@Test
	public void isNumberShouldReturnTrue() {
		String number = "1234";
		assertTrue(NumberValidator.getInstance().isNumber(number));
		number = "1.23";
		assertTrue(NumberValidator.getInstance().isNumber(number));
		number = "0.4E-4";
		assertTrue(NumberValidator.getInstance().isNumber(number));
		number = "1E+4";
		assertTrue(NumberValidator.getInstance().isNumber(number));
	}
	
	@Test
	public void isNumberShouldReturnFalse() {
		String number = "Y-";
		assertFalse(NumberValidator.getInstance().isNumber(number));
	}
	
	@Test(expected = NumberFormatException.class)
	public void parseIntoNumberShouldReturnException() {
		String number = "y--";
		NumberConverter.parseIntoNumber(number);
	}
	
	@Test
	public void testParseIntoNumber() {
		String number = "123" + Integer.MAX_VALUE;
		BigDecimal parsedNumber = NumberConverter.parseIntoNumber(number);
		assertEquals(number, parsedNumber.toString());
		number = "12341.3124151342425";
		parsedNumber = NumberConverter.parseIntoNumber(number);
		assertEquals(number, parsedNumber.toString());
	}
	
	@Test
	public void testParseIntoNumbers() {
		String[] numbers = new String[] {"312312413214", "13214141254123"};
		BigDecimal[] parsedNumbers = NumberConverter.parseIntoNumbers(numbers);
		for (int i = 0; i < parsedNumbers.length; i++) {
			assertEquals(NumberConverter.parseIntoNumber(numbers[i]),
													 	 parsedNumbers[i]);
		}
		numbers = new String[] {"124.1231412425124", "1231.412512412314"};
		parsedNumbers = NumberConverter.parseIntoNumbers(numbers);
		for (int i = 0; i < parsedNumbers.length; i++) {
			assertEquals(NumberConverter.parseIntoNumber(numbers[i]),
														 parsedNumbers[i]);
		}
		numbers = new String[] {"124125135324", "12413.413524335424"};
		parsedNumbers = NumberConverter.parseIntoNumbers(numbers);
		for (int i = 0; i < parsedNumbers.length; i++) {
			assertEquals(NumberConverter.parseIntoNumber(numbers[i]),
														 parsedNumbers[i]);
		}
	}
	
	@Test
	public void testCalculateSum() {
		String numbers[] = new String[] {"121414154" + Integer.MAX_VALUE,
										 "2.235154325234"};
		BigDecimal[] parsedNumbers = NumberConverter.parseIntoNumbers(numbers);
		BigDecimal sum = NumbersAction.getInstance()
											.calculateSum(parsedNumbers);
		logger.info("Sum: " + sum);
	}
	
	@Test
	public void testCalculatAvg() {
		String numbers[] = new String[] {"1", "1"};
		BigDecimal[] parsedNumbers = NumberConverter.parseIntoNumbers(numbers);
		BigDecimal avg = NumbersAction.getInstance()
											.calculateAvg(parsedNumbers);
		logger.info("Avg: " + avg);
	}
	
}
