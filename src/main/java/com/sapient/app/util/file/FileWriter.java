package com.sapient.app.util.file;

public interface FileWriter {
	void writeHeader(String[] headers);

	void writeData(String[] data);

	void close();
}
