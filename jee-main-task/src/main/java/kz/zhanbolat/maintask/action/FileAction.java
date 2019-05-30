package kz.zhanbolat.maintask.action;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.log4j.Logger;

import kz.zhanbolat.maintask.exceptions.MemoryAllocationException;
import kz.zhanbolat.maintask.valid.MemoryValidator;

public class FileAction {
	private static Logger logger = Logger.getLogger(FileAction.class);
	private static FileAction fileAction;
	public static final int MAX_SIZE_OF_DATA = 4096;
	
	private FileReader reader;
	private FileWriter writer;
	
	private FileAction() { }

	public static FileAction getInstance() {
		if (fileAction == null) {
			fileAction = new FileAction();
		}
		return fileAction;
	}
	
	public char[] loadData(String path) {
		char data[] = null;
		try {
			reader = new FileReader(path);
			int size = 0;
			size = (int)Files.size(new File(path).toPath());
			if (MemoryValidator.getInstance().isMemorySizeValid(size)) {
				data = new char[size];
			}
			reader.read(data);
		} catch (IOException e) {
			logger.error("Got problem in reading file." + e);
		} catch (MemoryAllocationException e) {
			logger.error("Got problem with allocating memory."
					+ " Datas will be set to null." + e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				logger.error("Got problem in closing stream." + e);
			}
		}
		return data;
	}
	
	public void writeData(File file, String data) {
		try {
			writer = new FileWriter(file.getPath().toString());
			writer.write(data);
		} catch (IOException e) {
			logger.error("Got problem in writing in the file." + e);
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				logger.error("Got problem in closing stream." + e);
			}
		}
	}
	
}
