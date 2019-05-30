package kz.zhanbolat.maintask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;

import org.apache.log4j.Logger;

import kz.zhanbolat.maintask.action.FileAction;
import kz.zhanbolat.maintask.converter.NumberConverter;
import kz.zhanbolat.maintask.generator.DataGenerator;
import kz.zhanbolat.maintask.action.NumbersAction;

public class App {
	static Logger logger = Logger.getLogger(App.class);
    public static void main(String args[]) {
		BufferedReader bf = new BufferedReader(
										new InputStreamReader(System.in));
		String bound = null;
		try {
			System.out.print("Enter the bound: ");
			bound = bf.readLine();
		} catch (IOException e) {
			logger.error("Got problem with IO." + e);
        }
		File file = new File("..\\data\\MainFile.txt");
		FileAction.getInstance().writeData(
				file, 
				new DataGenerator().generate(Integer.parseInt(bound)));
		char[] data = FileAction.getInstance().loadData(file.getPath());
		String loadedData = String.valueOf(data);
		String[] splitedData = loadedData.split("[\n ,]");
		BigDecimal[] numbers = NumberConverter.parseIntoNumbers(splitedData);
		logger.info("Array: " + Arrays.toString(numbers));
		BigDecimal sum = NumbersAction.getInstance().calculateSum(numbers);
		logger.info("sum: " + sum);
		BigDecimal avg = NumbersAction.getInstance().calculateAvg(numbers);
		logger.info("average: " + avg);
	}
}
