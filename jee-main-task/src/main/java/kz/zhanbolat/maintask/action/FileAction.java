package kz.zhanbolat.maintask.action;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import kz.zhanbolat.maintask.exceptions.MemoryAllocationException;
import kz.zhanbolat.maintask.reporter.ErrorReporter;
import kz.zhanbolat.maintask.valid.MemoryValidator;

public class FileAction {
	
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
			ErrorReporter.report("Got problem in reading file.");
			e.printStackTrace();
		} catch (MemoryAllocationException e) {
			ErrorReporter.report("Got problem with allocating memory."
					+ " Datas will be set to null");
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public void writeData(File file, String data) {
		try {
			writer = new FileWriter(file.getPath().toString());
			writer.write(data);
		} catch (IOException e) {
			ErrorReporter.report("Got problem in writing in the file");
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
