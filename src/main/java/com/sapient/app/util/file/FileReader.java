package com.sapient.app.util.file;

import java.io.IOException;
import java.util.List;

public interface FileReader {
	String[] readRow() throws IOException;

	List<String[]> readAllRows() throws IOException;
}
