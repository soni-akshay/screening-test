package com.sapient.app.util.file.factory;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sapient.app.exception.InvalidFileException;
import com.sapient.app.util.file.FileReader;
import com.sapient.app.util.file.FileWriter;
import com.sapient.app.util.file.impl.CsvFileReader;
import com.sapient.app.util.file.impl.CsvFileWriter;

@Component("abstractFileUtilFactory")
public class AbstractFileUtilFactory {

	public FileReader getFileReader(String filePath) throws InvalidFileException, IOException {
		if (StringUtils.isEmpty(filePath)) {
			throw new InvalidFileException("Invalid File.");
		}
		final String extension = FilenameUtils.getExtension(filePath).toUpperCase();
		switch (extension) {
		case "CSV":
			return new CsvFileReader(filePath);
		default:
			throw new InvalidFileException("Invalid File.");
		}
	}

	public FileWriter getFileWriter(String type, Writer writer) throws InvalidFileException {
		if (StringUtils.isEmpty(type)) {
			throw new InvalidFileException("Invalid File.");
		}
		switch (type.toUpperCase()) {
		case "CSV":
			return new CsvFileWriter(writer);
		default:
			throw new InvalidFileException("Invalid File Extension.");
		}
	}
}
