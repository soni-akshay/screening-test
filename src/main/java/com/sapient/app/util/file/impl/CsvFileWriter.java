package com.sapient.app.util.file.impl;

import java.io.IOException;
import java.io.Writer;

import com.sapient.app.util.file.FileWriter;

import au.com.bytecode.opencsv.CSVWriter;

public class CsvFileWriter implements FileWriter {
	
	private CSVWriter _csvWriter;

	public CsvFileWriter(Writer writer) {
		this._csvWriter = new CSVWriter(writer);
	}

	@Override
	public void writeHeader(String[] headers) {
		this._csvWriter.writeNext(headers);

	}

	@Override
	public void writeData(String[] data) {
		this._csvWriter.writeNext(data);
	}

	@Override
	public void close() {
		try {
			this._csvWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

