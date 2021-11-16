package com.sapient.app.util.file.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import au.com.bytecode.opencsv.CSVReader;

public class CsvFileReader implements com.sapient.app.util.file.FileReader {

	private CSVReader _reader;

	public CsvFileReader(String filePath) throws IOException {
		// _reader = new CSVReader(new FileReader(filePath));
		// the below one is temp. It should have overloaded method to load file from
		// different context
		_reader = new CSVReader(new InputStreamReader(new ClassPathResource("static/" + filePath).getInputStream()));
	}

	@Override
	public String[] readRow() throws IOException {
		return _reader.readNext();
	}

	@Override
	public List<String[]> readAllRows() throws IOException {
		return _reader.readAll();
	}
}
